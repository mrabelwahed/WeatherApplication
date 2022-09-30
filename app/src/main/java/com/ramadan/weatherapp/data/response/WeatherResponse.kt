package com.ramadan.weatherapp.data.response

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)