package com.example.currencyconverter.di

import com.example.currencyconverter.data.models.ConversionRates
import com.example.currencyconverter.data.models.ConversionRatesDeserializer
import com.example.currencyconverter.data.models.api.ApiInterface
import com.example.currencyconverter.main.MainRepository
import com.example.currencyconverter.main.MainRepositoryImpl
import com.example.currencyconverter.main.MainViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://v6.exchangerate-api.com"

val appModule = module {
    single {
        val gson: Gson = GsonBuilder()
            .registerTypeAdapter(ConversionRates::class.java, ConversionRatesDeserializer())
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiInterface::class.java)
    }

    single<MainRepository> {
        MainRepositoryImpl(get())
    }

    viewModel {
        MainViewModel(get())
    }
}
