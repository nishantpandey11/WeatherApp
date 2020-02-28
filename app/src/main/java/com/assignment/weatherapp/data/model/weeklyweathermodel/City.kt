package com.assignment.weatherapp.data.model.weeklyweathermodel

import android.os.Parcelable
import com.assignment.weatherapp.data.model.dailyweathermodel.Coord
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City (

	val name : String,
	val coord : Coord,
	val country : String
):Parcelable