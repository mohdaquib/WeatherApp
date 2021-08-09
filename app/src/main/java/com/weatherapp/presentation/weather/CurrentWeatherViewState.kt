package com.weatherapp.presentation.weather

import com.weatherapp.core.Event

data class CurrentWeatherViewState(
    val progress: Event<Boolean>,
    val error: Exception?,
    val data: CurrentDayWeatherModel?
)