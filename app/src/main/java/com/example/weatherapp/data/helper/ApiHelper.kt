package com.example.weatherapp.data.helper

import com.example.weatherapp.data.model.SunModel
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.network.ApiClient.Companion.getWeatherClient
import com.example.weatherapp.data.network.ApiInterface
import retrofit2.awaitResponse

class ApiHelper {
    suspend fun callApi(city:String): WeatherModel? {
        val retrofit = getWeatherClient()!!.create(ApiInterface::class.java)
        val response =retrofit.getWeatherData(city).awaitResponse()
        return if(response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
    suspend fun sunApi(city:String): SunModel? {
        val retrofit = getWeatherClient()!!.create(ApiInterface::class.java)
        val response =retrofit.getSunData(city).awaitResponse()
        return if(response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
}