package com.example.weatherapp.domain

import com.example.weatherapp.data.helper.ApiHelper

class DataRepository {
    companion object{
        val repository=DataRepository()
    }
    private val apiHelper= ApiHelper()
    suspend fun dataWeather(city:String) = apiHelper.callApi(city)
    suspend fun dataSun(city:String) = apiHelper.sunApi(city)
}