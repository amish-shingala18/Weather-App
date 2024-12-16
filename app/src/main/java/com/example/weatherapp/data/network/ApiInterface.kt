package com.example.weatherapp.data.network

import com.example.weatherapp.data.model.SunModel
import com.example.weatherapp.data.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("forecast")
    fun getWeatherData(
        @Query("q") city: String,
        @Query("units") units: String="metric",
        @Query("appid") appid: String="48c4cbb6a3df50fa9e48c65f44d03d1e"
    ) : Call<WeatherModel>

    @GET("weather")
    fun getSunData(
        @Query("q") city: String,
        @Query("units") units: String="metric",
        @Query("appid") appid: String="48c4cbb6a3df50fa9e48c65f44d03d1e"
    ) : Call<SunModel>

}