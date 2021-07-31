package com.weatherapp.domain.weather

import com.weatherapp.domain.Result
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(placeName: String): Result<Weather> {
        return weatherRepository.fetchWeatherData(placeName)
    }
}
