package com.weatherapp.domain.settings

import com.weatherapp.domain.Result
import javax.inject.Inject

class GetTemperatureUnitUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    operator fun invoke(): Result<TemperatureUnit> {
        return settingsRepository.temperatureUnit()
    }
}
