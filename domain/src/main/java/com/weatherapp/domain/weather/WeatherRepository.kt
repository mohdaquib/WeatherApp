package com.weatherapp.domain.weather

import com.weatherapp.domain.Result

interface WeatherRepository {
    suspend fun fetchWeatherData(placeName: String): Result<Weather>
}
