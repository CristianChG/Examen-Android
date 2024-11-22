package com.example.myapplication.domain

import com.example.myapplication.data.network.model.HistoricalEvent

class HistoricalRequirement {
    private val repository = HistoricalRepository()

    suspend operator fun invoke(page: Int): List<HistoricalEvent>? {
        return repository.getHistoricalEvents(page)
    }
}
