package com.weatherapp.presentation.places

import java.io.Serializable

data class Place(
    val name: String,
    val state: String,
    val postalCode: String,
    val lat: Double,
    val lng: Double
) : Serializable
