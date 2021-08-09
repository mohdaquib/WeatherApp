package com.weatherapp.data.openweathermap

import com.google.gson.annotations.SerializedName
import com.weatherapp.domain.weather.Sys

data class MainDto(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("grnd_level") val grndLevel: Int
)

data class WeatherDto(
    val id: Long,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class WindDto(val speed: Double, val deg: Double, val gust: Double)

data class SysDto(val type: Int, val id: Int, val sunrise: Int, val sunset: Int)

data class CloudsDto(val all: Int)

data class CoordDto(val lon: Double, val lat: Double)

data class CurrentDayWeatherDto(
    val coord: CoordDto,
    val weather: List<WeatherDto>,
    val base: String,
    val main: MainDto,
    val visibility: Int,
    val wind: WindDto,
    val clouds: CloudsDto,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)