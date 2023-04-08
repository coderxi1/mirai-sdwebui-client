package com.coderxi.sdwebui.client.service

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import com.coderxi.sdwebui.client.config.StableDiffusionConfig
import com.coderxi.sdwebui.client.exception.StableDiffusionException
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*
import java.util.concurrent.TimeUnit

object StableDiffusionService {

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(StableDiffusionConfig.clientTimeout, TimeUnit.MILLISECONDS)
            .writeTimeout(StableDiffusionConfig.clientTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(StableDiffusionConfig.clientTimeout, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun authHeader(): String {
        val authInfo = StableDiffusionConfig.authInfo
        if (!authInfo.enable)
            return "";
        return "Basic " + String(
            Base64.getEncoder().encode((authInfo.username + ":" + authInfo.password).toByteArray())
        )
    }

    fun post(path: String, jsonBody: Any): String {
        val request = Request.Builder()
            .url(StableDiffusionConfig.apiBaseUrl + path)
            .post(JSON.toJSONString(jsonBody).toRequestBody("application/json;charset=utf-8".toMediaType()))
            .addHeader("Authorization", authHeader())
            .build()
        val response = okHttpClient().newCall(request).execute()
        return checkJsonBody(response.body!!.string());
    }

    fun get(path: String, params: Map<String, String>? = null): String {
        var url: HttpUrl = (StableDiffusionConfig.apiBaseUrl + path).toHttpUrl()
        if (params != null) {
            val urlBuilder = url.newBuilder()
            params.forEach { (key, value) -> urlBuilder.addQueryParameter(key, value) }
            url = urlBuilder.build()
        }
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", authHeader())
            .build()
        val response = okHttpClient().newCall(request).execute()
        return checkJsonBody(response.body!!.string());
    }

    private fun checkJsonBody(jsonBody: String): String {
        try {
            val body = JSONObject.parseObject(jsonBody)
            if (body.containsKey("error")) {
                throw StableDiffusionException(body.getString("error"), body.getString("detail"))
            }
        } catch (e: StableDiffusionException) {
            throw e
        } catch (_: Exception) {
        }
        return jsonBody
    }

}