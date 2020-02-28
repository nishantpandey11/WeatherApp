package com.assignment.weatherapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.assignment.weatherapp.R
import com.assignment.weatherapp.data.model.dailyweathermodel.Main
import com.assignment.weatherapp.data.model.weeklyweathermodel.ForecastData
import com.assignment.weatherapp.databinding.ActivityForecastScreenBinding
import java.util.*
import kotlin.collections.ArrayList


class ForecastActivity : AppCompatActivity() {
    private lateinit var forecastBinding: ActivityForecastScreenBinding
    private val adapter = ForecastAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forecastBinding = DataBindingUtil.setContentView(this, R.layout.activity_forecast_screen)
        forecastBinding.recyclerView.adapter = adapter

        val intent = intent
        val args = intent.getBundleExtra("data")
        var items = args.getSerializable("it") as ArrayList<ForecastData>?

        items?.filter { c ->
            val cal = Calendar.getInstance()
            cal.time = Date(c.dt.toLong() * 1000)
            val hours = cal.get(Calendar.HOUR_OF_DAY)
            hours == 11
        }.apply {
            var main = ArrayList<Main>()
            for (item in this!!) {
                main.add(item.main)

            }
            adapter.submitList(main)
        }


    }
}
