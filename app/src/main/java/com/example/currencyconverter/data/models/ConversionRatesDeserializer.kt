package com.example.currencyconverter.data.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ConversionRatesDeserializer : JsonDeserializer<ConversionRates> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ConversionRates {
        val jsonObject = json?.asJsonObject ?: return ConversionRates()

        return ConversionRates(
            jsonObject.get("CAD")?.asDouble ?: 0.0,
            jsonObject.get("HKD")?.asDouble ?: 0.0,
            jsonObject.get("ISK")?.asDouble ?: 0.0,
            jsonObject.get("EUR")?.asDouble ?: 0.0,
            jsonObject.get("PHP")?.asDouble ?: 0.0,
            jsonObject.get("DKK")?.asDouble ?: 0.0,
            jsonObject.get("HUF")?.asDouble ?: 0.0,
            jsonObject.get("CZK")?.asDouble ?: 0.0,
            jsonObject.get("AUD")?.asDouble ?: 0.0,
            jsonObject.get("RON")?.asDouble ?: 0.0,
            jsonObject.get("SEK")?.asDouble ?: 0.0,
            jsonObject.get("IDR")?.asDouble ?: 0.0,
            jsonObject.get("INR")?.asDouble ?: 0.0,
            jsonObject.get("BRL")?.asDouble ?: 0.0,
            jsonObject.get("RUB")?.asDouble ?: 0.0,
            jsonObject.get("HRK")?.asDouble ?: 0.0,
            jsonObject.get("JPY")?.asDouble ?: 0.0,
            jsonObject.get("THB")?.asDouble ?: 0.0,
            jsonObject.get("CHF")?.asDouble ?: 0.0,
            jsonObject.get("SGD")?.asDouble ?: 0.0,
            jsonObject.get("PLN")?.asDouble ?: 0.0,
            jsonObject.get("BGN")?.asDouble ?: 0.0,
            jsonObject.get("CNY")?.asDouble ?: 0.0,
            jsonObject.get("NOK")?.asDouble ?: 0.0,
            jsonObject.get("NZD")?.asDouble ?: 0.0,
            jsonObject.get("ZAR")?.asDouble ?: 0.0,
            jsonObject.get("USD")?.asDouble ?: 0.0,
            jsonObject.get("MXN")?.asDouble ?: 0.0,
            jsonObject.get("ILS")?.asDouble ?: 0.0,
            jsonObject.get("GBP")?.asDouble ?: 0.0,
            jsonObject.get("KRW")?.asDouble ?: 0.0,
            jsonObject.get("MYR")?.asDouble ?: 0.0
        )
    }
}
