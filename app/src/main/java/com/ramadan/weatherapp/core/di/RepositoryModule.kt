package com.ramadan.weatherapp.core.di

import com.ramadan.weatherapp.data.repository.RepositoryImpl
import com.ramadan.weatherapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindWeatherRepository(
        characterRepositoryImpl: RepositoryImpl
    ): Repository
}