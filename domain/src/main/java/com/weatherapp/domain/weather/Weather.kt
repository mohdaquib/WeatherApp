package com.weatherapp.domain.weather

data class Temperature(val value: Double)

data class Pressure(val value: Double)

data class Humidity(val value: Double)

data class Weather(
    val description: String?,
    val type: WeatherType,
    val temperature: Temperature,
    val maxTemperature: Temperature,
    val minTemperature: Temperature,
    val pressure: Pressure,
    val humidity: Humidity,
    val wind: Wind
)

data class Wind(
    val speed: Double,
    val direction: Double
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