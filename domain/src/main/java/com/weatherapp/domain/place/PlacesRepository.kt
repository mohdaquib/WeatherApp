package com.weatherapp.domain.place

import com.weatherapp.domain.Result
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    suspend fun storePlace(place: Place)

    fun observePlaces(): Flow<Result<List<Place>>>

    fun loadPlace(postalCode: String): Flow<Result<Place>>

    suspend fun deletePlace(postalCode: String)
}