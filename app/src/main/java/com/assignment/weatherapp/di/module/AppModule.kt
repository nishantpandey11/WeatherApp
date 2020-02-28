package com.assignment.weatherapp.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.assignment.weatherapp.WeatherApp
import com.assignment.weatherapp.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApplication(): WeatherApp = WeatherApp()

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

}