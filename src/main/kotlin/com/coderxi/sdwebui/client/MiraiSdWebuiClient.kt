package com.coderxi.sdwebui.client

import com.coderxi.sdwebui.client.command.StableDiffusionCommand
import com.coderxi.sdwebui.client.config.StableDiffusionConfig
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin

object MiraiSdWebuiClient : KotlinPlugin(
    JvmPluginDescription(
        id = "com.coderxi.mirai-sdwebui-client",
        name = "Mirai stable-diffusion-webui Client",
        version = "0.1.2",
    ) {
        author("coderxi")
    }
) {
    override fun onEnable() {
        StableDiffusionConfig.reload()
        CommandManager.registerCommand(StableDiffusionCommand)
    }
}