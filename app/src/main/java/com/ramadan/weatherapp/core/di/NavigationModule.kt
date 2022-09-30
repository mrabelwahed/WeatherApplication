package com.ramadan.weatherapp.core.di

import com.ramadan.weatherapp.core.navigation.AppNavigator
import com.ramadan.weatherapp.core.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {
    @Binds
    abstract fun bindsAppNavigator(appNavigator: AppNavigator) : Navigator
}