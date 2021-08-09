package com.weatherapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.weatherapp.R
import com.weatherapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var placesAdapter: PlacesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        placesAdapter = PlacesAdapter()
        binding.places.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = placesAdapter
        }

        placesAdapter.onClick = {
            val bundle = bundleOf("place" to it)
            findNavController().navigate(R.id.showCityWeatherAction, bundle)
        }

        placesAdapter.onCloseClick = {
            homeViewModel.deletePlace(it.postalCode)
            placesAdapter.notifyDataSetChanged()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addLocation.setOnClickListener {
            findNavController().navigate(R.id.addPlaceFragment)
        }

        homeViewModel.viewState.observe(viewLifecycleOwner) {
            val data = it.data
            if (data.isNotEmpty()){
                placesAdapter.submitList(it.data)
                binding.places.visibility = View.VISIBLE
                binding.emptyView.visibility = View.GONE
            } else {
                binding.places.visibility = View.GONE
                binding.emptyView.visibility = View.VISIBLE
            }
        }
    }
}