package com.weatherapp.data.place

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Place(val name: String, val id: String, val country: String)