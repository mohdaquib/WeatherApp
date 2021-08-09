package com.weatherapp.domain.weather

data class Clouds(val all: Int)
data class Coord(val lon: Double, val lat: Double)
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double,
    val seaLevel: Int,
    val grndLevel: Int
)

data class Sys(
    val type: Int,
    val id: Int,
    val sunrise: Int,
    val sunset: Int
)

data class Weather(
    val id: Long,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class CurrentDayWeather(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

data class Wind(
    val speed: Double,
    val deg: Double,
    val gust: Double
)

enum class WeatherType {
    STORM,
    LIGHT_RAIN,
    RAIN,
    SNOW,
    FOG,
    CLEAR,
    LIGHT_CLOUDS,
    CLOUDS,
    UNDEFINED
}