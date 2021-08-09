package com.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weatherapp.data.database.entities.PlaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(place: PlaceEntity)

    @Query("SELECT * FROM places")
    fun loadAll(): Flow<List<PlaceEntity>>

    @Query("SELECT * FROM places WHERE name = :postalCode")
    fun loadPlace(postalCode: String): Flow<PlaceEntity>

    @Query("DELETE FROM places WHERE postalCode = :postalCode")
    suspend fun deletePlace(postalCode: String)
}