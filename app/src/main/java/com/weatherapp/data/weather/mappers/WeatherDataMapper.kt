package com.weatherapp.data.weather.mappers

import com.weatherapp.data.openweathermap.CurrentDayWeatherDto
import com.weatherapp.data.openweathermap.DataDto
import com.weatherapp.data.openweathermap.FiveDaysWeatherDto
import com.weatherapp.data.openweathermap.WeatherDto
import com.weatherapp.domain.weather.*

fun CurrentDayWeatherDto.toWeather(): CurrentDayWeather {
    return CurrentDayWeather(
        Coord(lon = this.coord.lon, lat = this.coord.lat),
        getWeatherList(weather = this.weather),
        base,
        Main(
            temp = this.main.temp,
            feels_like = this.main.feelsLike,
            temp_min = this.main.tempMin,
            temp_max = this.main.tempMax,
            pressure = this.main.pressure,
            humidity = this.main.humidity,
            seaLevel = this.main.seaLevel,
            grndLevel = this.main.grndLevel
        ),
        visibility,
        Wind(speed = wind.speed, deg = wind.deg, gust = wind.gust),
        Clouds(all = clouds.all),
        dt,
        sys, timezone, id, name, cod
    )
}

fun FiveDaysWeatherDto.toWeather(): FiveDaysWeather {
    return FiveDaysWeather(
        cod = cod,
        message = message,
        cnt = cnt,
        list = getDataList(list),
        city = City(
            id = this.city.id,
            name = this.city.name,
            coord = Coord(this.city.coord.lon, this.city.coord.lat),
            country = this.city.country,
            population = this.city.population,
            timezone = this.city.timezone,
            sunrise = this.city.sunrise,
            sunset = this.city.sunset
        )
    )
}

private fun getDataList(list: List<DataDto>): List<Data> {
    return list.map {
        Data(
            dt = it.dt,
            main = Main(
                temp = it.main.temp,
                feels_like = it.main.feelsLike,
                temp_min = it.main.tempMin,
                temp_max = it.main.tempMax,
                pressure = it.main.pressure,
                humidity = it.main.humidity,
                seaLevel = it.main.seaLevel,
                grndLevel = it.main.grndLevel
            ),
            weather = getWeatherList(it.weather),
            Clouds(all = it.clouds.all),
            Wind(speed = it.wind.speed, deg = it.wind.deg, gust = it.wind.gust),
            visibility = it.visibility,
            pop = it.pop,
            sys = it.sys,
            dt_txt = it.dt_txt
        )
    }
}

private fun getWeatherList(weather: List<WeatherDto>): List<Weather> {
    return weather.map {
        Weather(id = it.id, main = it.main, description = it.description, icon = it.icon)
    }
}

fun mapToWeatherType(weatherId: Long): WeatherType {
    // Based on weather code data found at:
    // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
    return when (weatherId) {
        in 200..232 -> WeatherType.STORM
        in 300..321 -> WeatherType.LIGHT_RAIN
        in 500..504 -> WeatherType.RAIN
        511L -> WeatherType.SNOW
        in 520..531 -> WeatherType.RAIN
        in 600..622 -> WeatherType.SNOW
        in 701..761 -> WeatherType.FOG
        761L, 781L -> WeatherType.STORM
        800L -> WeatherType.CLEAR
        801L -> WeatherType.LIGHT_CLOUDS
        in 802..804 -> WeatherType.CLOUDS
        // Change this to a icon that represents unknown
        else -> WeatherType.UNDEFINED
    }
}
