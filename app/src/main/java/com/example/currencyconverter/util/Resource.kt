package com.example.currencyconverter.util

sealed class Resource<T>(val data:T?,val message:String?) {

    class Success<T>(data:T):Resource<T>(data,null)
    class Failure<T>(message: String?):Resource<T>(null,message)



}