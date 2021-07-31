package com.weatherapp.domain.settings

import com.weatherapp.domain.Result

interface SettingsRepository {

    fun changeTemperatureUnit(unit: TemperatureUnit)

    fun settings(): Result<Settings>

    fun temperatureUnit(): Result<TemperatureUnit>
}
