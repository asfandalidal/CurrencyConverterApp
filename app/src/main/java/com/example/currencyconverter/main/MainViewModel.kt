package com.example.currencyconverter.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.models.ConversionRates
import com.example.currencyconverter.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    sealed class CurrencyEvent {
        class Success(val resultText: String) : CurrencyEvent()
        class Failure(val errorText: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion

    fun convertCurrency(
        amt: String,
        fromCurr: String,
        toCurrency: String
    ) {
        val amt = amt.toFloatOrNull()
        if (amt == null) {
            _conversion.value = CurrencyEvent.Failure("Not a valid amount")
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = CurrencyEvent.Loading
            val result = mainRepository.getCurrencyRates(fromCurr)
            when (result) {
                is Resource.Failure -> _conversion.value = CurrencyEvent.Failure(result.message ?: "Unknown error")
                is Resource.Success -> {
                    val rates = result.data?.conversion_rates ?: return@launch
                    val rate = getRateForCurrency(toCurrency, rates)
                    if (rate == null) {
                        _conversion.value = CurrencyEvent.Failure("Unexpected error")
                    } else {
                        val convertedCurrency = round(amt * rate * 100) / 100
                        _conversion.value = CurrencyEvent.Success(
                            "$amt $fromCurr = $convertedCurrency $toCurrency"
                        )
                    }
                }
            }
        }
    }

    private fun getRateForCurrency(currency: String, rates: ConversionRates) = when (currency) {
        "CAD" -> rates.CAD
        "HKD" -> rates.HKD
        "ISK" -> rates.ISK
        "EUR" -> rates.EUR
        "PHP" -> rates.PHP
        "DKK" -> rates.DKK
        "HUF" -> rates.HUF
        "CZK" -> rates.CZK
        "AUD" -> rates.AUD
        "RON" -> rates.RON
        "SEK" -> rates.SEK
        "IDR" -> rates.IDR
        "INR" -> rates.INR
        "BRL" -> rates.BRL
        "RUB" -> rates.RUB
        "HRK" -> rates.HRK
        "JPY" -> rates.JPY
        "THB" -> rates.THB
        "CHF" -> rates.CHF
        "SGD" -> rates.SGD
        "PLN" -> rates.PLN
        "BGN" -> rates.BGN
        "CNY" -> rates.CNY
        "NOK" -> rates.NOK
        "NZD" -> rates.NZD
        "ZAR" -> rates.ZAR
        "USD" -> rates.USD
        "MXN" -> rates.MXN
        "ILS" -> rates.ILS
        "GBP" -> rates.GBP
        "KRW" -> rates.KRW
        "MYR" -> rates.MYR
        else -> null
    }
}
