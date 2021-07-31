package com.weatherapp.di

import com.weatherapp.data.settings.SettingsDataSource
import com.weatherapp.domain.settings.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object SettingsModule {

    @Singleton
    @Provides
    fun provideSettingsModule(
        settingsRepository: SettingsDataSource
    ): SettingsRepository =
        settingsRepository
}
