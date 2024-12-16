package com.example.weatherapp.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.ListItem
import com.example.weatherapp.data.model.SunModel
import com.example.weatherapp.domain.DataRepository.Companion.repository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import java.util.Locale

@Suppress("DEPRECATION")
class WeatherViewModel : ViewModel() {
    var city: String? = null
    private val sunMutableLiveData  = MutableLiveData<SunModel?>()
    val sunLiveData : LiveData<SunModel?> = sunMutableLiveData
    private var weatherMutableLiveData = MutableLiveData<List<ListItem?>>()
    val weatherLiveData: MutableLiveData<List<ListItem?>> = weatherMutableLiveData
    private var cityMutableLiveData = MutableLiveData<String>()
    val cityLiveData: LiveData<String> = cityMutableLiveData
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    fun locationDetector(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            city="New Delhi"
            getWeather()
            getSun()
            Log.d("TAG", "locationDetector: =========================================")
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener {
            Log.d("TAG", "longitude:=================== ${it.longitude}")
            Log.d("TAG", "latitude:==================== ${it.latitude}")
            val addresses: List<Address>
            val longitude = it.longitude
            val latitude = it.latitude
            val geocoder = Geocoder(context, Locale.getDefault())
            addresses = geocoder.getFromLocation(latitude, longitude, 1)!!
            city = addresses[0].locality
            Log.d("TAG", "locationDetector: ===================================$city")
            getWeather()
            getSun()
        }
    }
    fun getWeather() {
        viewModelScope.launch {
            Log.d("TAG", "locationDetector: --------+++++++++++===========$city")
            val data = repository.dataWeather(city!!)
            cityMutableLiveData.value= data?.city?.name.toString()
            weatherMutableLiveData.value = data?.list!!
        }
    }
    fun getSun(){
        viewModelScope.launch {
            val sunData = repository.dataSun(city!!)
            Log.d("TAG", "getSun: ========================================$sunData")
            sunMutableLiveData.value= sunData!!
        }
    }
}