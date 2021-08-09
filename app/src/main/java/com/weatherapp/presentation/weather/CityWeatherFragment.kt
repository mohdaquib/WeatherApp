package com.weatherapp.presentation.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.weatherapp.R
import com.weatherapp.databinding.FragmentCityWeatherBinding
import com.weatherapp.presentation.places.Place
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_weather.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class CityWeatherFragment : Fragment() {
    private val cityWeatherViewModel: CityWeatherViewModel by viewModels()
    private lateinit var binding: FragmentCityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val place = arguments?.getSerializable("place") as Place?
        if (place != null) {
            cityWeatherViewModel.viewState.observe(viewLifecycleOwner) {
                it.progress.consume()?.let {
                    // show progress indicator
                    progress.visibility = View.VISIBLE
                }

                if (it.data != null) {
                    Log.d("Data", it.data.toString())
                    getFiveDaysWeather(place, it.data)
                } else {
                    progress.visibility = View.GONE
                    Log.d("Exception", it.error.toString())
                }
            }

            cityWeatherViewModel.getCurrentWeatherData(place.lat, place.lng)
        }
    }

    private fun getFiveDaysWeather(place: Place, currentDayWeatherModel: CurrentDayWeatherModel) {
        cityWeatherViewModel.fiveDaysWeatherViewState.observe(viewLifecycleOwner) {
            progress.visibility = View.GONE
            if (it.data != null) {
                val weatherModel = WeatherModel(
                    currentDayWeatherModel = currentDayWeatherModel,
                    fiveDaysWeatherModel = it.data
                )
                bindView(weatherModel)
            }
        }

        cityWeatherViewModel.getFiveDaysWeatherData(place.lat, place.lng)
    }

    private fun bindView(weatherModel: WeatherModel) {
        binding.place.text = weatherModel.fiveDaysWeatherModel.city.name
        binding.date.text = SimpleDateFormat(
            "EEE dd MMM yyyy",
            Locale.getDefault()
        ).format(Date(weatherModel.currentDayWeatherModel.dt.toLong() * 1000))
        val imageUrl =
            "https://openweathermap.org/img/wn/${weatherModel.currentDayWeatherModel.weather[0].icon}@4x.png"
        binding.weatherTypeImage.load(imageUrl)
        val temp = (weatherModel.currentDayWeatherModel.main.temp - 273).toInt()
        binding.temperature.text = "${temp} ℃"
        binding.weatherType.text = weatherModel.currentDayWeatherModel.weather[0].main

        val windSpeed = (weatherModel.currentDayWeatherModel.wind.speed * 3.6).toInt()
        binding.wind.text = "${windSpeed}km/h"
        binding.windLabel.text = "Wind"

        val humidity = weatherModel.currentDayWeatherModel.main.humidity
        binding.humidity.text = "${humidity}" + getString(R.string.percent)
        binding.humidityLabel.text = "Humidity"

        val maximumTemp = (weatherModel.currentDayWeatherModel.main.temp_max - 273).toInt()
        binding.maximumTemp.text = "${maximumTemp} ℃"
        binding.maximumTempLabel.text = "Maximum"

        val list: List<Data> = weatherModel.fiveDaysWeatherModel.list
        val forecastMap = getForecastMap(list)
        forecastMap
            .map {
                when (it.key) {
                    getNextDay(1) -> {
                        bindForecastView(it, firstDay, image1, firstDayTemp)
                    }

                    getNextDay(2) -> {
                        bindForecastView(it, secondDay, image2, secondDayTemp)
                    }

                    getNextDay(3) -> {
                        bindForecastView(it, thirdDay, image3, thirdDayTemp)
                    }

                    getNextDay(4) -> {
                        bindForecastView(it, forthDay, image4, forthDayTemp)
                    }

                    getNextDay(5) -> {
                        bindForecastView(it, fifthDay, image5, fifthDayTemp)
                    }
                }
            }
    }

    private fun getForecastMap(list: List<Data>): Map<String, Data> {
        val forecastMap = mutableMapOf<String, Data>()
        val days = arrayListOf<String>()
        val mappedArray = arrayListOf<Data>()

        list.mapIndexed { _, data ->
            if (days.contains(data.dt_txt.substringBefore(" ")).not()) {
                data.dt_txt.substringBefore(" ").let { days.add(it) }
            }
        }

        days.forEach { day ->
            val array = list.filter { it.dt_txt.substringBefore(" ") == day }

            val minTemp = array.minByOrNull { it.main.temp_min }?.main?.temp_min
            val maxTemp = array.maxByOrNull { it.main.temp_max }?.main?.temp_max

            array.forEach {
                it.main.temp_min = minTemp!!
                it.main.temp_max = maxTemp!!
                mappedArray.add(it)
            }
        }

        mappedArray.forEach {
            it.day = getDay(it.dt.toLong())
            forecastMap[it.day!!] = it
        }

        forecastMap
            .filter { it.key == getDay(Date().time) }

        return forecastMap
    }

    private fun bindForecastView(
        it: Map.Entry<String, Data>,
        day: AppCompatTextView,
        image: AppCompatImageView,
        dayTemp: AppCompatTextView
    ) {
        day.text = it.key
        image.load("https://openweathermap.org/img/wn/${it.value.weather[0].icon}@4x.png")
        dayTemp.text = (it.value.main.temp_max - 273).toInt().toString()
    }

    private fun getDay(dt: Long): String {
        return SimpleDateFormat("EE", Locale.getDefault()).format(Date(dt * 1000))
    }

    private fun getNextDay(daysToAdd: Long): String {
        val format = DateTimeFormatter.ofPattern("EE", Locale.getDefault())
        val nextDay = LocalDate.now().plusDays(daysToAdd)
        return nextDay.format(format)
    }
}