package com.example.myapplication.data.network

import android.util.Log
import com.example.myapplication.data.network.model.HistoricalEvent

class HistoricalAPIClient(private val api: HistoricalAPIService = NetworkModuleDI.apiService) {

    suspend fun getHistoricalEvents(page: Int): List<HistoricalEvent>? {
        return try {
            val response = api.getHistoricalEvents(page)
            if (response.isSuccessful) {
                Log.d("API_RESPONSE", "Datos recibidos: ${response.body()?.result?.data}")
                response.body()?.result?.data
            } else {
                Log.e("API_ERROR", "Error en la respuesta: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API_EXCEPTION", "Excepción al conectar: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}
