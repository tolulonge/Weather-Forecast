package com.tolulonge.weatherforecast.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.weatherforecast.Forecast
import com.tolulonge.weatherforecast.Place
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.Wind
import com.tolulonge.weatherforecast.databinding.ItemRvForecastDaysGalleryBinding
import com.tolulonge.weatherforecast.databinding.ItemRvPlaceWeatherBinding
import com.tolulonge.weatherforecast.databinding.ItemRvWindWeatherBinding

class ForecastDaysAdapter(
    private var allDays: List<Forecast>,
    private val onItemClicked: (Forecast) -> Unit
) : RecyclerView.Adapter<ForecastDaysAdapter.ForecastDaysViewHolder>() {

    class ForecastDaysViewHolder(private val binding: ItemRvForecastDaysGalleryBinding, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }


        fun bind(forecast: Forecast) {
            binding.apply {
                txtPhenomenonForecastGallery.text = forecast.phenomenon
                txtForecastDate.text = forecast.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDaysViewHolder {
        val view = ItemRvForecastDaysGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastDaysViewHolder(view){
            onItemClicked(allDays[it])
        }
    }

    override fun onBindViewHolder(holder: ForecastDaysViewHolder, position: Int) {
        if (allDays.isNotEmpty()) {
            val currentPlace = allDays[position]
            holder.bind(currentPlace)
        }
    }

    override fun getItemCount(): Int {
        return allDays.size
    }

}