package com.weatherapp.domain.weather

import com.weatherapp.domain.Result
import javax.inject.Inject

class CurrentDayWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(lat: Double, lng: Double): Result<CurrentDayWeather> {
        return weatherRepository.fetchWeatherData(lat, lng)
    }
}
