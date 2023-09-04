package com.natiqhaciyef.clotmobile.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NetworkURL{
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val okhttp = OkHttpClient.Builder().addInterceptor(logger)

    val BASE_URL = "https://techtive.tech/clot/"
}