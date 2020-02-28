package com.assignment.weatherapp.data.model.weeklyweathermodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class WeeklyWeatherForecast(

    val cod: Int,
    val message: Double,
    val cnt: Int,
    val list:List<ForecastData>,
    val city: City
):Parcelable
