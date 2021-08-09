package com.weatherapp.presentation.places

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.weatherapp.R
import kotlinx.android.synthetic.main.fragment_add_place.*
import java.util.*

class AddPlaceFragment : Fragment(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_place, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val targetPosition = LatLng(20.5937, 78.9629)
        val cameraPosition = CameraPosition.Builder()
            .target(targetPosition)
            .zoom(7f)
            .build()
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        googleMap.setOnMapClickListener {
            val geocoder = Geocoder(context, Locale.getDefault())
            val result = geocoder.getFromLocation(it.latitude, it.longitude, 2)
            var city: String? = null
            var state: String? = null
            var postalCode: String? = null
            if (result != null && result.size > 0) {
                city = getCityFromAddress(result[0]) ?: getCityFromAddress(result[1])
                state = result[0].adminArea ?: result[1].adminArea
                postalCode = result[0].postalCode ?: result[1].postalCode
            }

            if (city != null && state != null && postalCode != null){
                val place = Place(city, state, postalCode, it.latitude, it.longitude)
                val bundle = bundleOf("place" to place)
                view?.findNavController()?.navigate(R.id.showPlaceDetailAction, bundle)
            }
        }
    }

    private fun getCityFromAddress(result: Address) =
        if (result.locality != null) {
            result.locality
        } else {
            result.subAdminArea
        }
}