package com.example.currencyconverter.data.models

data class CurrencyResponse(
    val base_code: String,
    val conversion_rates: ConversionRates,
//    val result: String,
)