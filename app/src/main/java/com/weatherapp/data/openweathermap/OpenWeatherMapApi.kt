package com.weatherapp.data.openweathermap

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {
    @GET("weather")
    suspend fun getCurrentWeatherByPlaceCoord(
        @Query("lat") cityLat: Double,
        @Query("lon") cityLng: Double,
        @Query("appid") apiKey: String
    ): CurrentDayWeatherDto

    @GET("forecast")
    suspend fun getFiveDaysWeatherByPlaceCoord(
        @Query("lat") cityLat: Double,
        @Query("lon") cityLng: Double,
        @Query("appid") apiKey: String
    ): FiveDaysWeatherDto
}