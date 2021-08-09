package com.weatherapp.data.weather

import com.weatherapp.BuildConfig
import com.weatherapp.data.openweathermap.OpenWeatherMapApi
import com.weatherapp.data.weather.mappers.toWeather
import com.weatherapp.domain.Failure
import com.weatherapp.domain.Result
import com.weatherapp.domain.Success
import com.weatherapp.domain.weather.CurrentDayWeather
import com.weatherapp.domain.weather.FiveDaysWeather
import com.weatherapp.domain.weather.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

const val API_KEY = BuildConfig.OPENWEATHERMAP_API_KEY

@Singleton
class WeatherDataSource @Inject constructor(private val api: OpenWeatherMapApi) :
    WeatherRepository {

    override suspend fun fetchWeatherData(lat: Double, lng: Double): Result<CurrentDayWeather> {
        return try {
            val weatherDto = api.getCurrentWeatherByPlaceCoord(
                lat,
                lng,
                API_KEY
            )
            Success(weatherDto.toWeather())
        } catch (@Suppress("TooGenericExceptionCaught") exception: Exception) {
            Failure(exception)
        }
    }

    override suspend fun fetchFiveDaysWeatherData(
        lat: Double,
        lng: Double
    ): Result<FiveDaysWeather> {
        return try {
            val weatherDto = api.getFiveDaysWeatherByPlaceCoord(
                lat,
                lng,
                API_KEY
            )
            Success(weatherDto.toWeather())
        } catch (@Suppress("TooGenericExceptionCaught") exception: Exception) {
            Failure(exception)
        }
    }
}
