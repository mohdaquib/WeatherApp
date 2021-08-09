package com.weatherapp.presentation.places

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.domain.place.StorePlaceUseCase
import kotlinx.coroutines.launch

class PlacesViewModel @ViewModelInject constructor(
    val storePlaceUseCase: StorePlaceUseCase
) : ViewModel() {

    fun storePlace(place: Place) = viewModelScope.launch {
        storePlaceUseCase(
            com.weatherapp.domain.place.Place(
                place.name,
                place.state,
                place.postalCode,
                place.lat,
                place.lng
            )
        )
    }
}