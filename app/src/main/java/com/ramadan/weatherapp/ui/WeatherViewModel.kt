package com.ramadan.weatherapp.ui


import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramadan.weatherapp.data.response.WeatherResponse
import com.ramadan.weatherapp.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _weather_forecast: MutableLiveData<DataState<WeatherResponse>> = MutableLiveData()
    val weather_forecast: LiveData<DataState<WeatherResponse>> get() = _weather_forecast

    fun getWeatherFor(city: String) {
        _weather_forecast.value = DataState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getWeatherForecast(city)
                _weather_forecast.value = DataState.Success(response)
            } catch (e: Exception) {
                _weather_forecast.value = DataState.Error(e)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun formatData(dateStr: String): String {
        try {
            val format1 = SimpleDateFormat("yyyy-MM-dd hh:mm")
            val date = format1.parse(dateStr)
            val format2 = SimpleDateFormat("EEEE, dd MMM yyyy")
            return format2.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    @SuppressLint("SimpleDateFormat")
    fun formatHour(dateStr: String): String {
        try {
            val format1 = SimpleDateFormat("yyyy-MM-dd hh:mm")
            val date = format1.parse(dateStr)
            val format2 = SimpleDateFormat("hh:mm a")
            return format2.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}