package com.assignment.weatherapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assignment.weatherapp.R
import com.assignment.weatherapp.WeatherApp
import com.assignment.weatherapp.data.model.NetworkState
import com.assignment.weatherapp.data.model.dailyweathermodel.WeatherDataModel
import com.assignment.weatherapp.data.model.weeklyweathermodel.ForecastData
import com.assignment.weatherapp.databinding.ActivityMainBinding
import com.assignment.weatherapp.viewmodel.MainViewModel
import com.assignment.weatherapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
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


        ccp.setOnCountryChangeListener {
            Log.e("ccp-->", ccp.selectedCountryNameCode)
        }
        btn_go.setOnClickListener {
            val zip = et_zip_code.text
            if (zip.toString().isEmpty()) {
                et_zip_code.error = getString(R.string.error_zip)
            } else {
                val zipCon = zip.toString() + "," + ccp.selectedCountryNameCode
                Log.e("zipCon", zipCon)
                viewModel.getCurrentWeather(zipCon)
            }
        }

        btn_fetch_forecast.setOnClickListener {
            val zip = et_zip_code.text
            if (zip.toString().isEmpty()) {
                et_zip_code.error = getString(R.string.error_zip)
            } else {
                val zipCon = zip.toString() + "," + ccp.selectedCountryNameCode
                Log.e("zipCon", zipCon)
                viewModel.getWeeklyWeather(zipCon)
            }
        }

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.weatherData.observe(this, Observer {
            mainBinding.mainData = it.main


        })
        viewModel.weeklyWeatherData.observe(this, Observer {
            //navigate to next screen
            val intent = Intent(this, ForecastActivity::class.java)
            val args = Bundle()
            args.putSerializable("it", it as ArrayList<ForecastData>)
            intent.putExtra("data", args)
            startActivity(intent)

        })

        viewModel.networkState.observe(this, Observer {

            when (it) {
                NetworkState.START -> {
                    mainBinding.currentData.visibility = View.GONE
                    mainBinding.errorMsg.visibility = View.VISIBLE
                    mainBinding.errorMsg.text = getString(R.string.loading)
                }
                NetworkState.SUCCESS -> {
                    mainBinding.currentData.visibility = View.VISIBLE
                    mainBinding.errorMsg.visibility = View.GONE
                }
                NetworkState.ERROR -> {
                    mainBinding.currentData.visibility = View.GONE
                    mainBinding.errorMsg.visibility = View.VISIBLE
                    mainBinding.errorMsg.text = getString(R.string.error_msg)
                }
            }

        })
        viewModel.networkStateWeekly.observe(this, Observer {
            when (it) {
                NetworkState.START -> {
                    Toast.makeText(this, getString(R.string.fetcing), Toast.LENGTH_SHORT).show()
                }
                NetworkState.SUCCESS -> {

                }
                NetworkState.ERROR -> {
                    Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT).show()

                }
            }

        })
    }

}


