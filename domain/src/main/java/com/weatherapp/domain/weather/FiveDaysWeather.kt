package com.weatherapp.domain.weather

data class FiveDaysWeather(
    val cod: Int,
    val message: Int,
    val cnt: Int,
    val list: List<Data>,
    val city: City
)

data class Data(
    val dt: Int,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val sys: Sys,
    val dt_txt: String
)

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)