package com.weatherapp.di

import com.weatherapp.BuildConfig
import com.weatherapp.data.openweathermap.OpenWeatherMapApi
import com.weatherapp.data.weather.WeatherDataStore
import com.weatherapp.domain.weather.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val OPENWEATHERMAP_URL = BuildConfig.OPENWEATHERMAP_URL

@InstallIn(ApplicationComponent::class)
@Module
object OpenWeatherMapModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OPENWEATHERMAP_URL)
            .addConverterFactory(
                MoshiConverterFactory.create()
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideOpenWeatherMapApi(retrofit: Retrofit): OpenWeatherMapApi {
        return retrofit.create(OpenWeatherMapApi::class.java)
    }

    @Provides
    fun provideOpenWeatherMapRemoteDataSource(openWeatherMapApi: OpenWeatherMapApi): WeatherRepository {
        return WeatherDataStore(openWeatherMapApi)
    }
}
