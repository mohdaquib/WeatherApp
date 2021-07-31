package com.weatherapp.domain.settings

import com.weatherapp.domain.Result
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): Result<Settings> {
        return settingsRepository.settings()
    }
}
