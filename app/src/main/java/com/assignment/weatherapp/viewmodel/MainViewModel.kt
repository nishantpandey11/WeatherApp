package com.assignment.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.weatherapp.data.WeatherRepository
import com.assignment.weatherapp.data.model.NetworkState
import com.assignment.weatherapp.data.model.dailyweathermodel.WeatherDataModel
import com.assignment.weatherapp.data.model.weeklyweathermodel.ForecastData
import com.assignment.weatherapp.data.model.weeklyweathermodel.WeeklyWeatherForecast
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject


class MainViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    var weatherData: MutableLiveData<WeatherDataModel> = MutableLiveData()
    var weeklyWeatherData: MutableLiveData<List<ForecastData>> = MutableLiveData()
    var networkState: MutableLiveData<NetworkState> = MutableLiveData()
    var networkStateWeekly: MutableLiveData<NetworkState> = MutableLiveData()




    fun getWeeklyWeather(zip: String) {
        networkStateWeekly.value = NetworkState.START
        weatherRepository.getWeeklyWeather(zip)
            .subscribe(object : Observer<WeeklyWeatherForecast> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(value: WeeklyWeatherForecast) {
                    networkStateWeekly.postValue(NetworkState.SUCCESS)
                    weeklyWeatherData.postValue(value.list)
                    Log.e("getWeeklyWeather--->", value.city.country)
                }

                override fun onError(e: Throwable) {
                    networkStateWeekly.postValue(NetworkState.ERROR)
                    Log.e("error", e.toString())
                }

            })

    }


    fun getCurrentWeather(zip: String) {
        networkState.value = NetworkState.START
        weatherRepository.getWeather(zip)
            .subscribe(object : Observer<WeatherDataModel> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(value: WeatherDataModel) {
                    networkState.postValue(NetworkState.SUCCESS)
                    weatherData.postValue(value)
                    Log.e("getCurrentWeather--->", value.main.toString())
                }

                override fun onError(e: Throwable) {
                    networkState.postValue(NetworkState.ERROR)
                    Log.e("error", e.toString())
                    if (e is HttpException) {
                        val responseBody: ResponseBody? = e.response()?.errorBody()
                        Log.e("error-->", getErrorMessage(responseBody)+"")

                    } else {
                        Log.e("error", e.toString())
                    }
                }

            })
    }

    private fun getErrorMessage(responseBody: ResponseBody?): String? {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            jsonObject.getString("message")
        } catch (e: Exception) {
            e.message
        }
    }

}