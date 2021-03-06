package com.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weatherapp.data.database.entities.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}
