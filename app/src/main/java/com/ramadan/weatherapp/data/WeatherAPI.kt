package com.ramadan.weatherapp.data

import com.ramadan.weatherapp.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("v1/forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") key:String,
        @Query("q") city:String,
        @Query("days") days:Int,
        @Query("aqi") aqi:String,
        @Query("alerts") alerts : String
    ):WeatherResponse
}