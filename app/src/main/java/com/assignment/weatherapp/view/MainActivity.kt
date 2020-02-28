package com.assignment.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assignment.weatherapp.R
import com.assignment.weatherapp.WeatherApp
import com.assignment.weatherapp.data.model.NetworkState
import com.assignment.weatherapp.databinding.ActivityMainBinding
import com.assignment.weatherapp.viewmodel.MainViewModel
import com.assignment.weatherapp.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val app: WeatherApp = application as WeatherApp
        app.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.weatherData.observe(this, Observer {

        })
        viewModel.weeklyWeatherData.observe(this, Observer {

        })

        viewModel.networkState.observe(this, Observer {

            when (it) {
                NetworkState.START -> {

                }
                NetworkState.SUCCESS -> {

                }
                NetworkState.ERROR -> {

                }
            }

        })
    }
}
