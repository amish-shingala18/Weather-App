package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class SunModel(

	@field:SerializedName("visibility")
	val visibility: Int? = null,

	@field:SerializedName("timezone")
	val timezone: Int? = null,

	@field:SerializedName("main")
	val main: MainSun? = null,

	@field:SerializedName("clouds")
	val clouds: CloudsSun? = null,

	@field:SerializedName("sys")
	val sysSun: SysSun? = null,

	@field:SerializedName("dt")
	val dt: Int? = null,

	@field:SerializedName("coord")
	val coord: CoordSun? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItemSun?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("cod")
	val cod: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("base")
	val base: String? = null,

	@field:SerializedName("wind")
	val wind: WindSun? = null
)

data class CloudsSun(

	@field:SerializedName("all")
	val all: Int? = null
)

data class WeatherItemSun(

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("main")
	val main: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class CoordSun(

	@field:SerializedName("lon")
	val lon: Any? = null,

	@field:SerializedName("lat")
	val lat: Any? = null
)

data class MainSun(

	@field:SerializedName("temp")
	val temp: Any? = null,

	@field:SerializedName("temp_min")
	val tempMin: Any? = null,

	@field:SerializedName("grnd_level")
	val grndLevel: Int? = null,

	@field:SerializedName("humidity")
	val humidity: Int? = null,

	@field:SerializedName("pressure")
	val pressure: Int? = null,

	@field:SerializedName("sea_level")
	val seaLevel: Int? = null,

	@field:SerializedName("feels_like")
	val feelsLike: Any? = null,

	@field:SerializedName("temp_max")
	val tempMax: Any? = null
)

data class SysSun(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("sunrise")
	val sunrise: Int? = null,

	@field:SerializedName("sunset")
	val sunset: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: Int? = null
)

data class WindSun(

	@field:SerializedName("deg")
	val deg: Int? = null,

	@field:SerializedName("speed")
	val speed: Any? = null
)
