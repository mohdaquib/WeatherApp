package com.weatherapp.di

import android.content.Context
import com.weatherapp.data.database.PlaceDao
import com.weatherapp.data.place.PlacesDataSource
import com.weatherapp.domain.place.PlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object PlacesModule {

    @Singleton
    @Provides
    fun providePlacesLocalDataSource(
        placeDao: PlaceDao
    ): PlacesRepository = PlacesDataSource(placeDao)
}
