package com.coderxi.sdwebui.client.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName
import net.mamoe.mirai.console.data.value

object StableDiffusionConfig: AutoSavePluginConfig("config") {

    @ValueName("api_base_url")
    @ValueDescription("StableDiffusion API路径")
    val apiBaseUrl: String by value("http://127.0.0.1:7860")

    @ValueName("client_timeout")
    @ValueDescription("Http客户端超时时间(ms)")
    val clientTimeout: Long by value(60000L)

    @ValueName("img2img_max_size")
    @ValueDescription("图生图宽高上限")
    val img2imgMaxSize: Int by value(1000)

}