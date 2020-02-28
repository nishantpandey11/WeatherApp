package com.assignment.weatherapp.data.source.network

import com.assignment.weatherapp.data.model.dailyweathermodel.WeatherDataModel
import com.assignment.weatherapp.data.model.weeklyweathermodel.WeeklyWeatherForecast
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    //weather?zip=110089,IN&APPID=d0a641e8d1accb9bb0ff0ef9dbe7a64a
    @GET("/data/2.5/weather")
    fun getCurrentWeatherByZip(@Query("zip") zip: String, @Query("appid") appid: String): Observable<WeatherDataModel>


    //forecast?zip=94040&appid=b6907d289e10d714a6e88b30761fae22
    @GET("/data/2.5/forecast")
    fun getWeeklyWeatherByZip(@Query("zip") zip: String, @Query("appid") appid: String): Observable<WeeklyWeatherForecast>

}