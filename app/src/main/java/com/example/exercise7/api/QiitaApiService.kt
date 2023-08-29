package com.example.exercise7.api

import com.example.exercise7.dto.QiitaDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApiService {
    @GET("api/v2/items")
    fun getQiitaPosts(
        @Query("page")
        page: Int = 1,
        @Query("per_page")
        perPage: Int,
        @Query("query")
        query: String
    ) : Call<List<QiitaDto>>
}