package com.coderxi.sdwebui.client.util

import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.PlainText
import java.io.ByteArrayInputStream
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*

//提取PlainText -> 拆分为每一行 -> 通过"="分割key和value
fun MessageChain.toArgs(): Map<String, String> {
    val argLines = this.filterIsInstance(PlainText::class.java)
        .joinToString("\n")
        .trim()
        .split("\n")
    val args = hashMapOf<String, String>()
    argLines.forEach {
        if (it.contains("=")) {
            val split = it.split("=", limit = 2)
            args[split[0]] = split[1]
        }
    }
    return args
}

object Base64ImageUtil {
    fun base642Image(base64: String): ByteArrayInputStream {
        return ByteArrayInputStream(Base64.getDecoder().decode(base64.toByteArray(StandardCharsets.UTF_8)))
    }

    fun base642Image(base64: Any): ByteArrayInputStream {
        return base642Image(base64.toString())
    }

    fun image2base64(imageUrl: String): String {
        var base64 = ""
        val inputStream = URL(imageUrl).openConnection().getInputStream()
        inputStream.use { base64 = Base64.getEncoder().encodeToString(it.readBytes()) }
        return "data:image/png;base64,$base64"
    }
}