package com.example.weatherapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData

class MyApplication : Application() {
    companion object{
        lateinit var myApplication: MyApplication
    }

    override fun onCreate() {
        checkInternet(this)
        super.onCreate()
    }
    val liveData = MutableLiveData<Boolean>()
    fun checkInternet(context : Context){
        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
        connectivityManager.registerNetworkCallback(networkRequest,
            object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    liveData.postValue(true)
                }

                override fun onUnavailable() {
                    liveData.postValue(false)
                }
            })
    }
}