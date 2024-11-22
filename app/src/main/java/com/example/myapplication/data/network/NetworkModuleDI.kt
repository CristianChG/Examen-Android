package com.example.myapplication.data.network

import com.example.myapplication.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDI {

    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("X-Parse-Application-Id", Constants.APPLICATION_ID)
                    .header("X-Parse-REST-API-Key", Constants.API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(getOkHttpClient())
        .addConverterFactory(gsonFactory)
        .build()

    val apiService: HistoricalAPIService = retrofit.create(HistoricalAPIService::class.java)
}
