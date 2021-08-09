package com.weatherapp.di

import com.weatherapp.BuildConfig
import com.weatherapp.data.openweathermap.OpenWeatherMapApi
import com.weatherapp.data.weather.WeatherDataSource
import com.weatherapp.domain.weather.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val OPENWEATHERMAP_URL = BuildConfig.OPENWEATHERMAP_URL

@InstallIn(ApplicationComponent::class)
@Module
object OpenWeatherMapModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OPENWEATHERMAP_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideOpenWeatherMapApi(retrofit: Retrofit): OpenWeatherMapApi {
        return retrofit.create(OpenWeatherMapApi::class.java)
    }

    @Provides
    fun provideOpenWeatherMapRemoteDataSource(openWeatherMapApi: OpenWeatherMapApi): WeatherRepository {
        return WeatherDataSource(openWeatherMapApi)
    }
}
