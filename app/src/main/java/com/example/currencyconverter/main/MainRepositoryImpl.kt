package com.example.currencyconverter.main

import com.example.currencyconverter.data.models.CurrencyResponse
import com.example.currencyconverter.data.models.api.ApiInterface
import com.example.currencyconverter.util.Resource

class MainRepositoryImpl(private val api:ApiInterface):MainRepository {

    override suspend fun getCurrencyRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getCurrencyRates(base)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Failure("No data available")
            } else {
                Resource.Failure(response.message())
            }
        } catch (e: Exception) {
            Resource.Failure("An error occurred: ${e.message}")
        }
    }

}