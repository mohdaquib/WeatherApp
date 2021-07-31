package com.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.weatherapp.data.database.AppDatabase
import com.weatherapp.data.database.PlaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "weatherapp-db").build()
    }

    @Singleton
    @Provides
    fun providePlaceDao(database: AppDatabase): PlaceDao {
        return database.placeDao()
    }
}
