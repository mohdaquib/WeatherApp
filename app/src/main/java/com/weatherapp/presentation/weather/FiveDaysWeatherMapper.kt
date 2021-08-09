package com.weatherapp.presentation.weather

import com.weatherapp.domain.weather.Coord
import com.weatherapp.domain.weather.Data
import com.weatherapp.domain.weather.FiveDaysWeather
import com.weatherapp.domain.weather.Weather
import javax.inject.Inject

class FiveDaysWeatherMapper @Inject constructor() {

    fun map(fiveDaysWeather: FiveDaysWeather): FiveDaysWeatherModel {
        return FiveDaysWeatherModel(
            cod = fiveDaysWeather.cod,
            message = fiveDaysWeather.message,
            cnt = fiveDaysWeather.cnt,
            list = getDataList(fiveDaysWeather.list),
            city = com.weatherapp.presentation.weather.City(
                id = fiveDaysWeather.city.id,
                name = fiveDaysWeather.city.name,
                coord = Coord(fiveDaysWeather.city.coord.lon, fiveDaysWeather.city.coord.lat),
                country = fiveDaysWeather.city.country,
                population = fiveDaysWeather.city.population,
                timezone = fiveDaysWeather.city.timezone,
                sunrise = fiveDaysWeather.city.sunrise,
                sunset = fiveDaysWeather.city.sunset
            )
        )
    }

    private fun getDataList(list: List<Data>): List<com.weatherapp.presentation.weather.Data> {
        return list.map {
            Data(
                dt = it.dt,
                main = com.weatherapp.presentation.weather.Main(
                    temp = it.main.temp,
                    feels_like = it.main.feels_like,
                    temp_min = it.main.temp_min,
                    temp_max = it.main.temp_max,
                    pressure = it.main.pressure,
                    humidity = it.main.humidity,
                    seaLevel = it.main.seaLevel,
                    grndLevel = it.main.grndLevel
                ),
                weather = getWeatherList(it.weather),
                com.weatherapp.presentation.weather.Clouds(all = it.clouds.all),
                com.weatherapp.presentation.weather.Wind(
                    speed = it.wind.speed,
                    deg = it.wind.deg,
                    gust = it.wind.gust
                ),
                visibility = it.visibility,
                pop = it.pop,
                sys = Sys(it.sys.type, it.sys.id, it.sys.sunrise, it.sys.sunset),
                dt_txt = it.dt_txt
            )
        }
    }

    private fun getWeatherList(weather: List<Weather>): List<com.weatherapp.presentation.weather.Weather> {
        return weather.map {
            com.weatherapp.presentation.weather.Weather(
                id = it.id,
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        }
    }
}