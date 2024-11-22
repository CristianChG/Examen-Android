package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class HistoricalResponse(
    @SerializedName("result") val result: HistoricalResult
)

data class HistoricalResult(
    @SerializedName("code") val code: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("data") val data: List<HistoricalEvent>
)
