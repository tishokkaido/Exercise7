package com.example.exercise7.repository

import com.example.exercise7.api.QiitaApiClient
import com.example.exercise7.dto.QiitaDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QiitaPostRepository {
    fun fetchQiitaPost(listener: QiitaApiCallback) {
        QiitaApiClient.newInstance().service.getQiitaPosts(perPage = 20, query = "kotlin")
            .enqueue(object : Callback<List<QiitaDto>> {
                override fun onResponse(
                    call: Call<List<QiitaDto>>,
                    response: Response<List<QiitaDto>>
                ) {
                    if (response.isSuccessful) {
                        // 通信成功
                        listener.onSuccess(response.body().orEmpty())
                    } else {
                        // 通信失敗
                        onFailure(call, IllegalStateException("Api Access Failure"))
                    }
                }

                override fun onFailure(call: Call<List<QiitaDto>>, t: Throwable) {
                    listener.onFailure(t)
                }
            })
    }

    interface QiitaApiCallback {
        fun onSuccess(result: List<QiitaDto>)
        fun onFailure(t: Throwable)
    }
}