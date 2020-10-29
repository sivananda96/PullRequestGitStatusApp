package com.example.pullrequestgitstatusapp.network

import okhttp3.OkHttpClient

object NetworkUtils {
    // Adds header to the OkHttpClient making the request.
    val httpClient: OkHttpClient
        get() = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .method(original.method(), original.body())
                            .build()

                    chain.proceed(request)
                }
                .build()
}
