package com.assignment.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.weatherapp.R
import com.assignment.weatherapp.data.model.dailyweathermodel.Main
import com.assignment.weatherapp.databinding.ItemWeatherBinding


class ForecastAdapter : ListAdapter<Main, ForecastAdapter.ForecastViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Main> =
            object : DiffUtil.ItemCallback<Main>() {
                override fun areItemsTheSame(oldItem: Main, newItem: Main): Boolean {
                    return false
                }

                override fun areContentsTheSame(oldItem: Main, newItem: Main): Boolean {
                    return false
                }
            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemBinding: ItemWeatherBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.item_weather, parent, false
        )
        return ForecastViewHolder(itemBinding)

    }


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val currentItem: Main? = getItem(position)
        holder.bind(currentItem)
    }


    inner class ForecastViewHolder(private val itemBinding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(delivery: Main?) {
            itemBinding.mainData = delivery

        }

    }

}