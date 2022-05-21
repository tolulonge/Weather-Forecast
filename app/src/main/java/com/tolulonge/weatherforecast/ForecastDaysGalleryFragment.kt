package com.tolulonge.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tolulonge.weatherforecast.databinding.FragmentForecastDaysGalleryBinding
import com.tolulonge.weatherforecast.databinding.FragmentMainWeatherBinding
import com.tolulonge.weatherforecast.presentation.adapter.ForecastDaysAdapter
import com.tolulonge.weatherforecast.presentation.adapter.PlacesListAdapter
import com.tolulonge.weatherforecast.presentation.adapter.WindsListAdapter


class ForecastDaysGalleryFragment : Fragment() {

    private var _binding: FragmentForecastDaysGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var forecastDaysAdapter: ForecastDaysAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentForecastDaysGalleryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forecastDaysAdapter = ForecastDaysAdapter(forecast){
            Toast.makeText(requireContext(), it.phenomenon, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.fragment_main_weather)
        }




        binding.apply {
            rvForecastDaysGallery.adapter = forecastDaysAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

data class Forecast(val phenomenon: String,val date: String)

val forecast = listOf(Forecast("Sunny", "20th \nMay \n2022"),
    Forecast("Winter", "8th \nMay \n2022"),
    Forecast("Cloudy", "24th \nMay \n2022"),
    Forecast("Sunshine", "21st \nMay \n2022"))