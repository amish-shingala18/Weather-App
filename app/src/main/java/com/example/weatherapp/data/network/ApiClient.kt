package com.example.weatherapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        private var baseURL ="https://api.openweathermap.org/data/2.5/"
        private var retrofit : Retrofit?= null
        fun getWeatherClient(): Retrofit? {
            if(retrofit==null){
                retrofit=Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}