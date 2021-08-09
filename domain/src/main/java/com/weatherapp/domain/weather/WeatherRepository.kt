package com.weatherapp.domain.weather

import com.weatherapp.domain.Result

interface WeatherRepository {
    suspend fun fetchWeatherData(lat: Double, lng: Double): Result<CurrentDayWeather>

    suspend fun fetchFiveDaysWeatherData(lat: Double, lng: Double): Result<FiveDaysWeather>
}
