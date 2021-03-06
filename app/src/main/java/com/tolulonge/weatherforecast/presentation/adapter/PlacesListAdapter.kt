package com.tolulonge.weatherforecast.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.core.util.loadHeaderGifs
import com.tolulonge.weatherforecast.databinding.ItemRvPlaceWeatherBinding
import com.tolulonge.weatherforecast.presentation.state.model.PresentationPlace

class PlacesListAdapter : RecyclerView.Adapter<PlacesListAdapter.PlacesViewHolder>() {

    inner class PlacesViewHolder(private val binding: ItemRvPlaceWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(presentationPlace: PresentationPlace) {
            binding.apply {
                txtPlaceName.text = presentationPlace.name
                txtPhenomenonName.text = presentationPlace.phenomenon
                txtTemperatureRangePlace.text = binding.root.resources.getString(
                    R.string.places_temp_range,
                    presentationPlace.tempmin ?: "_",
                    presentationPlace.tempmax ?: "_"
                )
                imgPhenomenonDesc.loadHeaderGifs(presentationPlace.phenomenon ?: "")
                imgOpenDetailsPage.setOnClickListener {
                    onItemClickListener?.let {
                        it(presentationPlace)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val view =
            ItemRvPlaceWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlacesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.from_top)
        val currentPlace = differ.currentList[position]
        holder.apply {
            animation.duration = 1000
            itemView.startAnimation(animation)
            bind(currentPlace)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<PresentationPlace>() {
        override fun areItemsTheSame(
            oldItem: PresentationPlace,
            newItem: PresentationPlace
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: PresentationPlace,
            newItem: PresentationPlace
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((PresentationPlace) -> Unit)? = null

    fun setOnItemClickListener(listener: (PresentationPlace) -> Unit) {
        onItemClickListener = listener
    }


}