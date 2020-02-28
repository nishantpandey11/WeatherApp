package com.assignment.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.weatherapp.data.WeatherRepository
import com.assignment.weatherapp.data.model.NetworkState
import com.assignment.weatherapp.data.model.weathermodel.WeatherDataModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    var weatherData: MutableLiveData<WeatherDataModel> = MutableLiveData()
    var weeklyWeatherData: MutableLiveData<List<WeatherDataModel>> = MutableLiveData()
    var networkState: MutableLiveData<NetworkState> = MutableLiveData()


    init {
        getCurrentWeather()
        getWeeklyWeather()
    }

    private fun getWeeklyWeather() {
        networkState.value = NetworkState.START
        weatherRepository.getWeeklyWeather("110089,IN")
            .subscribe(object : Observer<Any> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(value: Any) {
                    networkState.postValue(NetworkState.SUCCESS)
                    Log.e("data", "done")
                }

                override fun onError(e: Throwable) {
                    networkState.postValue(NetworkState.ERROR)
                }

            })

    }



    private fun getCurrentWeather() {
        networkState.value = NetworkState.START
        weatherRepository.getWeather("110089,IN")
            .subscribe(object : Observer<WeatherDataModel> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(value: WeatherDataModel) {
                    networkState.postValue(NetworkState.SUCCESS)
                    weatherData.postValue(value)
                    Log.e("data", value.main.toString())
                }

                override fun onError(e: Throwable) {
                    networkState.postValue(NetworkState.ERROR)
                }

            })
    }

}