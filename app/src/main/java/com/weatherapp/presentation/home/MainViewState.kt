package com.weatherapp.presentation.home

import com.weatherapp.presentation.places.Place

data class MainViewState(
    val data: List<Place> = emptyList(),
    val error: Exception? = null
)