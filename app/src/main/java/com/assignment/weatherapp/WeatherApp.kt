package com.assignment.weatherapp

import android.app.Application
import com.assignment.weatherapp.di.component.AppComponent
import com.assignment.weatherapp.di.component.DaggerAppComponent

class WeatherApp :Application(){
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }
}