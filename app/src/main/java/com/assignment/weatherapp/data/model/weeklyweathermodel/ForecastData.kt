package com.assignment.weatherapp.data.model.weeklyweathermodel

import android.os.Parcelable
import com.assignment.weatherapp.data.model.dailyweathermodel.*
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class ForecastData (

	val dt : Long,
	val main : Main
	//val weather : List<Weather>,
	//val clouds : Clouds,
	//val wind : Wind,
	//val rain : Rain,
	//val sys : Sys,
	//val dt_txt : String
):Parcelable