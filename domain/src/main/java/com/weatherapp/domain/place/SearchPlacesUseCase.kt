package com.weatherapp.domain.place

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPlacesUseCase @Inject constructor(private val placesRepository: PlacesRepository) {

    operator fun invoke(placeName: Flow<String>): Flow<Result<Place>> {
        return placesRepository.searchPlace(placeName)
    }
}
