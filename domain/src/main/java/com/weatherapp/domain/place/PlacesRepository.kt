package com.weatherapp.domain.place

import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    suspend fun storePlace(place: Place)

    fun observePlaces(): Flow<Result<List<Place>>>

    fun searchPlace(placeName: Flow<String>): Flow<Result<Place>>
}