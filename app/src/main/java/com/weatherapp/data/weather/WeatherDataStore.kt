package com.weatherapp.data.weather

import com.weatherapp.BuildConfig
import com.weatherapp.data.openweathermap.OpenWeatherMapApi
import com.weatherapp.data.weather.mappers.toWeather
import com.weatherapp.domain.Failure
import com.weatherapp.domain.Result
import com.weatherapp.domain.Success
import com.weatherapp.domain.weather.Weather
import com.weatherapp.domain.weather.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

const val API_KEY = BuildConfig.OPENWEATHERMAP_API_KEY

@Singleton
class WeatherDataStore @Inject constructor(private val api: OpenWeatherMapApi) :
    WeatherRepository {
    override suspend fun fetchWeatherData(placeName: String): Result<Weather> {

        return try {
            val weatherDto = api.getWeatherByCityName(
                placeName,
                API_KEY
            )
            Success(weatherDto.toWeather())
        } catch (@Suppress("TooGenericExceptionCaught") exception: Exception) {
            Failure(exception)
        }
    }
}
