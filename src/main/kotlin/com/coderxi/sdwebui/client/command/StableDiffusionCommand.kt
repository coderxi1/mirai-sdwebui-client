package com.coderxi.sdwebui.client.command

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import com.coderxi.sdwebui.client.MiraiSdWebuiClient
import com.coderxi.sdwebui.client.MiraiSdWebuiClient.reload
import com.coderxi.sdwebui.client.config.StableDiffusionConfig
import com.coderxi.sdwebui.client.model.Img2ImgParams
import com.coderxi.sdwebui.client.model.Txt2ImgParams
import com.coderxi.sdwebui.client.service.StableDiffusionService
import com.coderxi.sdwebui.client.util.*
import com.coderxi.sdwebui.client.util.Base64ImageUtil
import com.coderxi.sdwebui.client.util.Base64ImageUtil.image2base64
import com.coderxi.sdwebui.client.util.showAlias
import com.coderxi.sdwebui.client.util.toArgs
import com.coderxi.sdwebui.client.util.toEntity
import net.mamoe.mirai.console.command.CommandContext
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.nextEvent
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage

@OptIn(ConsoleExperimentalApi::class)
object StableDiffusionCommand : CompositeCommand(
    MiraiSdWebuiClient,
    "sd", "StableDiffusion",
    description = "stable-diffusion-webui-api调用"
) {

    @SubCommand("reload", "重载")
    @Description("重载配置文件")
    suspend fun txt2img(context: CommandContext) {
        StableDiffusionConfig.reload()
        context.sender.sendMessage(context.originalMessage.quote() + "重载完成")
    }

    @SubCommand("txt2img", "文生图")
    @Description("调用/sdapi/v1/txt2img接口")
    suspend fun txt2img(context: CommandContext, @Name("参数") ignore: MessageChain) {
        val result = StableDiffusionService.post("/sdapi/v1/txt2img", context.originalMessage.toArgs().toEntity(Txt2ImgParams::class))
        val images = JSON.parseObject(result).getJSONArray("images").map(Base64ImageUtil::base642Image)
        context.sender.sendMessage(buildMessageChain {
            +context.originalMessage.quote()
            images.forEach {
                +it.uploadAsImage(context.sender.subject!!)
            }
        })
    }

    @SubCommand("img2img", "图生图")
    @Description("调用/sdapi/v1/img2img接口")
    suspend fun img2img(context: CommandContext, @Name("图片") ignore: MessageChain, @Name("参数") ignore2: MessageChain) {
        var initImage = context.originalMessage.findIsInstance<Image>()
        if (initImage == null) {
            context.sender.sendMessage(context.originalMessage.quote() + "请提供一张图片")
            val waitImageMsgEvent = context.sender.globalEventChannel().nextEvent<MessageEvent> { it.sender.id == context.sender.user?.id }
            val waitImage = waitImageMsgEvent.message.findIsInstance<Image>()
            if (waitImage == null) {
                waitImageMsgEvent.subject.sendMessage(context.originalMessage.quote() + "未提供图片，已取消")
                return
            } else {
                initImage = waitImage
            }
        }

        val params = context.originalMessage.toArgs().toEntity(Img2ImgParams::class)
        val limit = StableDiffusionConfig.img2imgMaxSize.toDouble()
        var w = initImage.width.toDouble()
        var h = initImage.height.toDouble()
        val s = w / h
        if (w > limit) {
            w = limit
            h = w / s
        }
        if (h > limit) {
            h = limit
            w = h * s
        }
        if (params.width == null || params.width!! > w.toInt()) {
            params.width = w.toInt()
        }
        if (params.height == null || params.height!! > h.toInt()) {
            params.height = h.toInt()
        }
        params.initImages = listOf(image2base64(initImage.queryUrl()))

        val result = StableDiffusionService.post("/sdapi/v1/img2img", params)
        val images = JSON.parseObject(result).getJSONArray("images").map(Base64ImageUtil::base642Image)
        context.sender.sendMessage(buildMessageChain {
            +context.originalMessage.quote()
            images.forEach {
                +it.uploadAsImage(context.sender.subject!!)
            }
        })
    }

    @SubCommand("model", "当前模型")
    @Description("调用/sdapi/v1/options接口查询sd_model_checkpoint")
    suspend fun getCurrentModel(context: CommandContext) {
        val modelName = JSON.parseObject(StableDiffusionService.get("/sdapi/v1/options")).getString("sd_model_checkpoint")
        context.sender.sendMessage(context.originalMessage.quote() + modelName)
    }

    @SubCommand("model load", "加载模型")
    @Description("调用/sdapi/v1/options接口设置sd_model_checkpoint")
    suspend fun setModel(context: CommandContext, modelName: String) {
        StableDiffusionService.post("/sdapi/v1/options", mapOf("sd_model_checkpoint" to modelName))
        context.sender.sendMessage(context.originalMessage.quote() + "加载完成")
    }

    @SubCommand("model list", "模型列表")
    @Description("调用/sdapi/v1/sd-models接口")
    suspend fun models(context: CommandContext) {
        val result = StableDiffusionService.get("/sdapi/v1/sd-models")
        val names = JSON.parseArray(result).map { (it as JSONObject).getString("title") }
        context.sender.sendMessage(buildMessageChain {
            +context.originalMessage.quote()
            +names.mapIndexed { index, value -> "【$index】$value" }.joinToString("\n")
        })
    }

    @SubCommand("samplers", "采样方法列表")
    @Description("调用/sdapi/v1/samplers接口")
    suspend fun samplers(context: CommandContext) {
        val result = StableDiffusionService.get("/sdapi/v1/samplers")
        val names = JSON.parseArray(result).map { (it as JSONObject).getString("name") }
        context.sender.sendMessage(buildMessageChain {
            +context.originalMessage.quote()
            +names.mapIndexed { index, value -> "【$index】$value" }.joinToString("\n")
        })
    }

    @SubCommand("args", "参数")
    @Description("列出某接口所有可设置参数")
    suspend fun txt2imgArgs(context: CommandContext, @Name("txt2img/img2img") funName: String) {
        context.sender.sendMessage(buildMessageChain {
            +context.originalMessage.quote()
            +when (funName.lowercase()) {
                "txt2img" -> Txt2ImgParams::class.showAlias()
                "img2img" -> Img2ImgParams::class.showAlias()
                else -> "错误的接口名"
            }
        })
    }

}