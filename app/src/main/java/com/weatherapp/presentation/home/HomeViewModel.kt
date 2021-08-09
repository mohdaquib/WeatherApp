package com.weatherapp.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.weatherapp.domain.Failure
import com.weatherapp.domain.Success
import com.weatherapp.domain.place.DeletePlaceUseCase
import com.weatherapp.domain.place.GetPlacesUseCase
import com.weatherapp.presentation.places.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel @ViewModelInject constructor(
    val getPlacesUseCase: GetPlacesUseCase,
    val deletePlaceUseCase: DeletePlaceUseCase
) : ViewModel() {

    val viewState = getPlacesUseCase()
        .map { result ->
            when (result) {
                is Success -> MainViewState(result.data.map {
                    Place(
                        it.name,
                        it.state,
                        it.postalCode,
                        it.lat,
                        it.lng
                    )
                })
                is Failure -> MainViewState(error = result.exception)
                else -> MainViewState(error = Exception("Something went wrong"))
            }
        }.asLiveData(viewModelScope.coroutineContext)

    fun deletePlace(postalCode: String) = viewModelScope.launch(Dispatchers.IO) {
        deletePlaceUseCase(postalCode)
    }
}