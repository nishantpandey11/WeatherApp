package com.assignment.weatherapp.data


import com.assignment.weatherapp.BuildConfig
import com.assignment.weatherapp.data.model.weathermodel.WeatherDataModel
import com.assignment.weatherapp.data.source.network.ApiInterface
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiInterface: ApiInterface) {

     fun getWeather(zip:String): Observable<WeatherDataModel> {
        return apiInterface.getCurrentWeatherByZip(zip,BuildConfig.APP_ID)
    }

    fun getWeeklyWeather(zip:String): Observable<Any> {
        return apiInterface.getWeeklyWeatherByZip(zip,BuildConfig.APP_ID)
    }

}