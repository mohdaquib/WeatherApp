package com.weatherapp.domain.weather

import com.weatherapp.domain.Result
import javax.inject.Inject

class FiveDaysWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lng: Double) : Result<FiveDaysWeather> {
        return weatherRepository.fetchFiveDaysWeatherData(lat, lng)
    }
}