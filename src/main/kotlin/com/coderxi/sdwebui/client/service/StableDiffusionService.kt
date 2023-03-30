package com.coderxi.sdwebui.client.service

import com.alibaba.fastjson2.JSON
import com.coderxi.sdwebui.client.config.StableDiffusionConfig
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

object StableDiffusionService {

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(StableDiffusionConfig.clientTimeout, TimeUnit.MILLISECONDS)
            .writeTimeout(StableDiffusionConfig.clientTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(StableDiffusionConfig.clientTimeout, TimeUnit.MILLISECONDS)
            .build()
    }

    fun post(path: String, jsonBody: Any): String {
        val request = Request.Builder()
            .url(StableDiffusionConfig.apiBaseUrl + path)
            .post(JSON.toJSONString(jsonBody).toRequestBody("application/json;charset=utf-8".toMediaType()))
            .build()
        val response = okHttpClient().newCall(request).execute()
        return response.body!!.string();
    }

    fun get(path: String, params: Map<String, String>? = null): String {
        var url: HttpUrl = (StableDiffusionConfig.apiBaseUrl + path).toHttpUrl()
        if (params != null) {
            val urlBuilder = url.newBuilder()
            params.forEach { (key, value) -> urlBuilder.addQueryParameter(key, value) }
            url = urlBuilder.build()
        }
        val request = Request.Builder().url(url).build()
        val response = okHttpClient().newCall(request).execute()
        return response.body!!.string();
    }

}