package com.weatherapp.presentation.weather

data class FiveDaysWeatherViewState(
    val error: Exception? = null,
    val data: FiveDaysWeatherModel? = null
)