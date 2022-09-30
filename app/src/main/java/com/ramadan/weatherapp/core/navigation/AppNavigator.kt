package com.ramadan.weatherapp.core.navigation

import androidx.fragment.app.FragmentActivity
import com.ramadan.weatherapp.R
import com.ramadan.weatherapp.ui.CurrentWeatherFragment
import javax.inject.Inject

class AppNavigator @Inject constructor(private val activity: FragmentActivity): Navigator {

    override fun navigateTo(screen: Screen) {
        val fragment = when(screen){
            Screen.WEATHER_SCREEN -> CurrentWeatherFragment()
        }
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container , fragment)
            .addToBackStack(fragment.javaClass.canonicalName)
            .commit()
    }
}
