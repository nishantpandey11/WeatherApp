package com.assignment.weatherapp.data.model

data class NetworkState constructor(
    val status: Status?,
    val msg: String?
) {

    enum class Status {
        START, SUCCESS, ERROR
    }

    companion object {
        val SUCCESS: NetworkState = NetworkState(Status.SUCCESS, "Success")
        val START: NetworkState = NetworkState(Status.START, "Running")
        val ERROR: NetworkState = NetworkState(Status.ERROR, "Failed")
    }


}