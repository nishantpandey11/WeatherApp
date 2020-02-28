package com.assignment.weatherapp.data.model.dailyweathermodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Main (

	val temp : Double,
	val feels_like : Double,
	val temp_min : Double,
	val temp_max : Double,
	val pressure : Int,
	val humidity : Int
):Parcelable