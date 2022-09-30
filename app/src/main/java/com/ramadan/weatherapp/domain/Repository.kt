package com.ramadan.weatherapp.domain

import com.ramadan.weatherapp.data.response.WeatherResponse

interface Repository  {
    suspend fun getWeatherForecast(city:String) : WeatherResponse
}