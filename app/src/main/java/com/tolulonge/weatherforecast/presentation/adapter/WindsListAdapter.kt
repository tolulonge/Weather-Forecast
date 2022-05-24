package com.tolulonge.weatherforecast.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.weatherforecast.databinding.ItemRvWindWeatherBinding
import com.tolulonge.weatherforecast.presentation.state.model.PresentationWind

class WindsListAdapter : RecyclerView.Adapter<WindsListAdapter.WindViewHolder>() {

    class WindViewHolder(private val binding: ItemRvWindWeatherBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(wind: PresentationWind) {
            binding.apply {
                txtWindName.text = wind.name
                txtDirectionName.text = wind.direction
                txtWindSpeedRange.text = "${wind.speedmin} to ${wind.speedmax}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindViewHolder {
        val view = ItemRvWindWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WindViewHolder(view)
    }

    override fun onBindViewHolder(holder: WindViewHolder, position: Int) {
            val currentWind = differ.currentList[position]
            holder.bind(currentWind)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<PresentationWind>() {
        override fun areItemsTheSame(
            oldItem: PresentationWind,
            newItem: PresentationWind
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: PresentationWind,
            newItem: PresentationWind
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}