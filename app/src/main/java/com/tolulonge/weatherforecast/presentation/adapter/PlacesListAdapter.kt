package com.tolulonge.weatherforecast.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.weatherforecast.Place
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.databinding.ItemRvPlaceWeatherBinding

class PlacesListAdapter(
    private var allPlaces: List<Place>,
    private val onItemClicked: (Place) -> Unit
) : RecyclerView.Adapter<PlacesListAdapter.PlacesViewHolder>() {

    class PlacesViewHolder(private val binding: ItemRvPlaceWeatherBinding, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.findViewById<ImageView>(R.id.img_open_details_page).setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(place: Place) {
            binding.apply {
                txtPlaceName.text = place.name
                txtPhenomenonName.text = place.phenomenon
                txtTemperatureRangePlace.text = place.temperature
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val view = ItemRvPlaceWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlacesViewHolder(view) {
            onItemClicked(allPlaces[it])
        }
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        if (allPlaces.isNotEmpty()) {
            val currentPlace = allPlaces[position]
            holder.bind(currentPlace)
        }
    }

    override fun getItemCount(): Int {
        return allPlaces.size
    }

}