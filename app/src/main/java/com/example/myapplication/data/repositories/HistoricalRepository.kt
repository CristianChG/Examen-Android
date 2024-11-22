package com.example.myapplication.domain

import com.example.myapplication.data.network.HistoricalAPIClient
import com.example.myapplication.data.network.model.HistoricalEvent

class HistoricalRepository {
    private val apiClient = HistoricalAPIClient()

    suspend fun getHistoricalEvents(page: Int): List<HistoricalEvent>? {
        return apiClient.getHistoricalEvents(page)
    }
}
