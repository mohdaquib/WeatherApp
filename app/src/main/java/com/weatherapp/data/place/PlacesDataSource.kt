package com.weatherapp.data.place

import com.weatherapp.data.database.PlaceDao
import com.weatherapp.data.database.entities.PlaceEntity
import com.weatherapp.data.database.entities.fromPlace
import com.weatherapp.data.database.entities.toPlace
import com.weatherapp.domain.Result
import com.weatherapp.domain.Success
import com.weatherapp.domain.place.Place
import com.weatherapp.domain.place.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlacesDataSource @Inject constructor(
    private val placeDao: PlaceDao
) : PlacesRepository {

    override suspend fun storePlace(place: Place) {
        val placeEntity = fromPlace(place = place)
        placeDao.insert(placeEntity)
    }

    override fun observePlaces(): Flow<Result<List<Place>>> {
        return placeDao.loadAll()
            .map { placeEntities -> Success(placeEntities.map { it.toPlace() }) }
    }

    override fun loadPlace(postalCode: String): Flow<Result<Place>> {
        return placeDao.loadPlace(postalCode)
            .map { value: PlaceEntity -> Success(value.toPlace()) }
    }

    override suspend fun deletePlace(postalCode: String) {
        placeDao.deletePlace(postalCode)
    }
}