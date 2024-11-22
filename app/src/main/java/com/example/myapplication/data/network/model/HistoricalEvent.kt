package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class HistoricalEvent(
    @SerializedName("date") val date: String,
    @SerializedName("description") val description: String,
    @SerializedName("lang") val lang: String,
    @SerializedName("category1") val category1: String,
    @SerializedName("category2") val category2: String,
    @SerializedName("granularity") val granularity: String,
    @SerializedName("objectId") val objectId: String
)
