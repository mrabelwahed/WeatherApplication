package com.ramadan.weatherapp.data.repository

import com.ramadan.weatherapp.core.common.AppConst.API_KEY
import com.ramadan.weatherapp.data.WeatherAPI
import com.ramadan.weatherapp.data.response.WeatherResponse
import com.ramadan.weatherapp.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api : WeatherAPI) : Repository {
    override suspend fun getWeatherForecast(city: String): WeatherResponse {
        return api.getWeatherForecast(
            key = API_KEY,
            city = city,
            days = 3,
            aqi = "no",
            alerts = "no",
        )
    }
}