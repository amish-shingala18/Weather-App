package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.view.adapter.WeatherAdapter
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val weatherViewModel by viewModels<WeatherViewModel>()
    private lateinit var binding : ActivityMainBinding
    private lateinit var weatherAdapter: WeatherAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sheet()
        locationPermission()
        searchCity()
        weatherViewModel.locationDetector(this)
        weatherViewModel.cityLiveData.observe(this) {
            binding.txtCityName.text = it
        }
        weatherViewModel.weatherLiveData.observe(this) {
            weatherAdapter = WeatherAdapter(it,weatherViewModel)
            binding.rvWeather.adapter=weatherAdapter
            Log.d("TAG", "onCreate:Weather ========================================$it")
            binding.txtDegree.text = it[0]?.main?.temp.toString()
            binding.txtWeatherDesc.text =
                it[0]?.weather?.get(0)?.description?.split(" ")
                    ?.joinToString(" ") { word ->
                        word.replaceFirstChar(Char::uppercase)
                    }
            binding.txtMinMaxTemp.text =
                "H:${it[0]?.main?.tempMax} L:${it[0]?.main?.tempMin}"
            binding.txtSeaLevel.text = it[0]?.main?.seaLevel.toString()
            binding.txtHumidity.text = it[0]?.main?.humidity.toString()
            binding.txtWind.text = it[0]?.wind?.speed.toString()
            binding.txtClouds.text = it[0]?.clouds?.all.toString()
            binding.txtWind.text = it[0]?.wind?.speed.toString()
        }
        weatherViewModel.sunLiveData.observe(this) { sysSun ->
            val sunrise = sysSun?.sysSun?.sunrise?.times(1000)
            val sunset = sysSun?.sysSun?.sunset?.times(1000)

            val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

            val sunriseTime = timeFormat.format(sunrise)
            val sunsetTime = timeFormat.format(sunset)

            binding.txtSunrise.text = sunriseTime
            binding.txtSunset.text = "Sunset: $sunsetTime"
        }

    }
    private fun locationPermission(): Boolean {
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION),100
            )
            return false
        }else{
            return true
        }
    }
    @SuppressLint("ResourceType")
    fun sheet(){
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        // Set initial state to half-expanded
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        // Set the half-expanded height
        bottomSheetBehavior.peekHeight = resources.displayMetrics.heightPixels / 2

    }
    private fun searchCity(){
        binding.svWeather.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                weatherViewModel.city = p0
                weatherViewModel.getWeather()
                weatherViewModel.getSun()
                binding.svWeather.clearFocus()
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }
}