package com.example.countries.data.sources.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkhttpClient {
    fun getClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BODY }
        )
        .build()
}