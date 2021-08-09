package com.weatherapp.presentation.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.core.Event
import com.weatherapp.domain.Failure
import com.weatherapp.domain.Success
import com.weatherapp.domain.weather.CurrentDayWeatherUseCase
import com.weatherapp.domain.weather.FiveDaysWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityWeatherViewModel @ViewModelInject constructor(
    val currentDayWeatherUseCase: CurrentDayWeatherUseCase,
    val fiveDayWeatherUseCase: FiveDaysWeatherUseCase,
    private val currentDayWeatherMapper: CurrentDayWeatherMapper,
    private val fiveDaysWeatherMapper: FiveDaysWeatherMapper
) : ViewModel() {
    private val _viewState = MutableLiveData<CurrentWeatherViewState>()
    val viewState: LiveData<CurrentWeatherViewState>
        get() = _viewState

    private val _fiveDaysWeatherViewState = MutableLiveData<FiveDaysWeatherViewState>()
    val fiveDaysWeatherViewState: LiveData<FiveDaysWeatherViewState>
        get() = _fiveDaysWeatherViewState

    fun getCurrentWeatherData(lat: Double, lng: Double) = viewModelScope.launch(Dispatchers.IO) {
        emitUiState(Event(true))
        when (val weatherResult = currentDayWeatherUseCase(lat, lng)) {
            is Success -> {
                val currentWeatherData = currentDayWeatherMapper.map(weatherResult.data)
                emitUiState(showSuccess = currentWeatherData)
            }
            is Failure -> {
                emitUiState(showError = weatherResult.exception)
            }
        }
    }

    fun getFiveDaysWeatherData(lat: Double, lng: Double) = viewModelScope.launch(Dispatchers.IO) {
        when (val weatherResult = fiveDayWeatherUseCase(lat, lng)) {
            is Success -> {
                val fiveDaysWeatherData = fiveDaysWeatherMapper.map(weatherResult.data)
                _fiveDaysWeatherViewState.postValue(FiveDaysWeatherViewState(data = fiveDaysWeatherData))
            }

            is Failure -> {
                _fiveDaysWeatherViewState.postValue(FiveDaysWeatherViewState(error = weatherResult.exception))
            }
        }
    }

    private fun emitUiState(
        showProgress: Event<Boolean> = Event(false),
        showError: Exception? = null,
        showSuccess: CurrentDayWeatherModel? = null
    ) {
        val viewState = CurrentWeatherViewState(
            showProgress,
            showError,
            showSuccess
        )
        _viewState.postValue(viewState)
    }
}