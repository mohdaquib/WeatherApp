package com.weatherapp.presentation.weather

import com.weatherapp.domain.weather.CurrentDayWeather
import com.weatherapp.domain.weather.Weather
import javax.inject.Inject

class CurrentDayWeatherMapper @Inject constructor() {

    fun map(currentDayWeather: CurrentDayWeather): CurrentDayWeatherModel {
        return CurrentDayWeatherModel(
            coord = Coord(currentDayWeather.coord.lon, currentDayWeather.coord.lat),
            weather = getWeatherList(currentDayWeather.weather),
            base = currentDayWeather.base,
            main = Main(
                currentDayWeather.main.temp,
                currentDayWeather.main.feels_like,
                currentDayWeather.main.temp_min,
                currentDayWeather.main.temp_max,
                currentDayWeather.main.pressure,
                currentDayWeather.main.humidity,
                currentDayWeather.main.seaLevel,
                currentDayWeather.main.grndLevel
            ),
            visibility = currentDayWeather.visibility,
            wind = Wind(
                currentDayWeather.wind.speed,
                currentDayWeather.wind.deg,
                currentDayWeather.wind.gust
            ),
            clouds = Clouds(currentDayWeather.clouds.all),
            dt = currentDayWeather.dt,
            sys = Sys(
                currentDayWeather.sys.type,
                currentDayWeather.sys.id,
                currentDayWeather.sys.sunrise,
                currentDayWeather.sys.sunset
            ),
            timezone = currentDayWeather.timezone,
            id = currentDayWeather.id,
            name = currentDayWeather.name,
            cod = currentDayWeather.cod
        )
    }

    private fun getWeatherList(list: List<Weather>): List<com.weatherapp.presentation.weather.Weather> {
        return list.map {
            com.weatherapp.presentation.weather.Weather(
                id = it.id,
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        }
    }
}