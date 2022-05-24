package com.tolulonge.weatherforecast.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.weatherforecast.databinding.ItemRvForecastDaysGalleryBinding
import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecastGallery
import com.tolulonge.weatherforecast.presentation.state.model.PresentationPlace

class ForecastDaysAdapter : RecyclerView.Adapter<ForecastDaysAdapter.ForecastDaysViewHolder>() {

    inner class ForecastDaysViewHolder(private val binding: ItemRvForecastDaysGalleryBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(forecast: PresentationForecastGallery) {
            binding.apply {
                txtPhenomenonForecastGallery.text = forecast.dPhenomenon
                txtForecastDate.text = forecast.date
                forecastDaysCard.setOnClickListener {
                    onItemClickListener?.let {
                        it(forecast)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDaysViewHolder {
        val view = ItemRvForecastDaysGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastDaysViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastDaysViewHolder, position: Int) {
        val animation =
            AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)
        val presentationForecast = differ.currentList[position]
        holder.apply {
            itemView.startAnimation(animation)
            bind(presentationForecast)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<PresentationForecastGallery>() {
        override fun areItemsTheSame(
            oldItem: PresentationForecastGallery,
            newItem: PresentationForecastGallery
        ): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(
            oldItem: PresentationForecastGallery,
            newItem: PresentationForecastGallery
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((PresentationForecastGallery) -> Unit)? = null

    fun setOnItemClickListener(listener: (PresentationForecastGallery) -> Unit) {
        onItemClickListener = listener
    }

}