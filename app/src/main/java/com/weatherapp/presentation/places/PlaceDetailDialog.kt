package com.weatherapp.presentation.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.weatherapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.place_detail_layout.*

@AndroidEntryPoint
class PlaceDetailDialog : BottomSheetDialogFragment() {
    private val placesViewModel: PlacesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.place_detail_layout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val place = arguments?.getSerializable("place") as Place
        city.text = place.name
        state.text = place.state + place.postalCode

        add.setOnClickListener {
            placesViewModel.storePlace(place)
            this@PlaceDetailDialog.findNavController().popBackStack()
            this@PlaceDetailDialog.findNavController().popBackStack()
        }
    }
}