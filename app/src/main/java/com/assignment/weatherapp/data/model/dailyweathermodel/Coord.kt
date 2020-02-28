package com.assignment.weatherapp.data.model.dailyweathermodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coord (

	val lon : Double,
	val lat : Double
):Parcelable