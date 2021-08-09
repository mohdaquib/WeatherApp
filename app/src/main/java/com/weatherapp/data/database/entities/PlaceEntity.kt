package com.weatherapp.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.weatherapp.domain.place.Place

@Entity(
    tableName = "places"
)
data class PlaceEntity(
    val name: String,
    val state: String,
    val postalCode: String,
    val lat: Double,
    val lng: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun fromPlace(place: Place) = PlaceEntity(place.name, place.state, place.postalCode, place.lat, place.lng)
fun PlaceEntity.toPlace() = Place(this.name, this.state, this.postalCode, this.lat, this.lng)
