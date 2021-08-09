package com.weatherapp.domain.place

import javax.inject.Inject

class DeletePlaceUseCase @Inject constructor(private val placesRepository: PlacesRepository) {

    suspend operator fun invoke(postalCode: String) {
        return placesRepository.deletePlace(postalCode)
    }
}