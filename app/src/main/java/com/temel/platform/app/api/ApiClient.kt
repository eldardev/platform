package com.temel.platform.app.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiClient @Inject constructor() {
    companion object {
        const val TIMEOUT: Long = 10
    }

    inline fun <reified ApiService> getService(baseUrl: String): ApiService {
        return create<ApiService>(baseUrl)
    }

    inline fun <reified T> create(baseUrl: String): T {

        val httpLoggingInterceptor =
            HttpLoggingInterceptor { message -> Timber.tag("okHttp").d(message) }

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(baseUrl)
            .client(client)
            .build()

        return retrofit.create(T::class.java)
    }
}