package com.weatherapp.domain.settings

sealed class TemperatureUnit

object Celsius : TemperatureUnit()
object Fahrenheit : TemperatureUnit()
