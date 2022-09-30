package com.ramadan.weatherapp.core.navigation

interface Navigator {
    fun navigateTo(screen : Screen)
}

enum class Screen{
    WEATHER_SCREEN
}