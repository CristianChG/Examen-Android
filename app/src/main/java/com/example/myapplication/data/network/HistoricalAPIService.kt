package com.example.myapplication.data.network

import com.example.myapplication.data.network.model.HistoricalResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface HistoricalAPIService {

    @POST("functions/hello")
    suspend fun getHistoricalEvents(
        @Query("page") page: Int
    ): Response<HistoricalResponse>
}
