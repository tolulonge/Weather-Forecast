package com.tolulonge.weatherforecast.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.weatherforecast.Place
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.Wind
import com.tolulonge.weatherforecast.databinding.ItemRvPlaceWeatherBinding
import com.tolulonge.weatherforecast.databinding.ItemRvWindWeatherBinding

class WindsListAdapter(
    private var allWinds: List<Wind>,
) : RecyclerView.Adapter<WindsListAdapter.WindViewHolder>() {

    class WindViewHolder(private val binding: ItemRvWindWeatherBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(wind: Wind) {
            binding.apply {
                txtWindName.text = wind.name
                txtDirectionName.text = wind.direction
                txtWindSpeedRange.text = wind.windSpeedRange
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindViewHolder {
        val view = ItemRvWindWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WindViewHolder(view)
    }

    override fun onBindViewHolder(holder: WindViewHolder, position: Int) {
        if (allWinds.isNotEmpty()) {
            val currentPlace = allWinds[position]
            holder.bind(currentPlace)
        }
    }

    override fun getItemCount(): Int {
        return allWinds.size
    }

}