package com.example.currencyconverter.data.models.api

import com.example.currencyconverter.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v6/241f75876985e25244a6a8d2/latest/USD")
    suspend fun getCurrencyRates(
        @Query("base_code") baseCode:String
    ):Response<CurrencyResponse>

}