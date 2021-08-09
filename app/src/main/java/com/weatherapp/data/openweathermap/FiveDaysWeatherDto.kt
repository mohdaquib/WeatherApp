package com.weatherapp.data.openweathermap

import com.weatherapp.domain.weather.Sys

data class FiveDaysWeatherDto(
    val cod: Int,
    val message: Int,
    val cnt: Int,
    val list: List<DataDto>,
    val city: CityDto
)

data class CityDto(
    val id: Int,
    val name: String,
    val coord: CoordDto,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)

data class DataDto(
    val dt: Int,
    val main: MainDto,
    val weather: List<WeatherDto>,
    val clouds: CloudsDto,
    val wind: WindDto,
    val visibility: Int,
    val pop: Double,
    val rain: RainDto,
    val sys: Sys,
    val dt_txt: String
)

data class RainDto(
    val `3h`: String
)
