package com.weatherapp.domain.place

import com.weatherapp.domain.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPlacesUseCase @Inject constructor(private val placesRepository: PlacesRepository) {

    operator fun invoke(): Flow<Result<List<Place>>> {
        return placesRepository.observePlaces()
    }
}
