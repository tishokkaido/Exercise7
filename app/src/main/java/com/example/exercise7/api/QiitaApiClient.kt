package com.example.exercise7.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class QiitaApiClient {
    val service: QiitaApiService
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(QiitaApiService::class.java)
        }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder().also {
                it.addInterceptor { chain ->
                    val currentRequest = chain.request()
                    val newRequest = currentRequest.newBuilder()
                        .header(HEADER_NAME, HEADER_VALUE)
                        .method(currentRequest.method(), currentRequest.body())
                        .build()
                    return@addInterceptor chain.proceed(newRequest)
                }
            }.readTimeout(TIME_OUT, TimeUnit.SECONDS)
            return builder.build()
        }

    companion object {
        // QiitaのベースURL
        const val BASE_URL = "https://qiita.com"
        // ヘッダー情報
        const val HEADER_NAME = "Accept"
        const val HEADER_VALUE = "application/json"
        // セッションタイムアウトの値（秒）
        const val TIME_OUT = 30L

        // QiitaApiClientのインスタンス
        private var instance: QiitaApiClient? = null

        /**
         * QiitaApiClientのインスタンスを取得
         *
         * @return QiitaApiClient
         */
        fun newInstance() = instance ?: synchronized(this) {
            instance ?: QiitaApiClient().also { instance = it }
        }
    }
}