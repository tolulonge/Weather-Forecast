package com.tolulonge.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tolulonge.weatherforecast.databinding.FragmentMainWeatherBinding
import com.tolulonge.weatherforecast.presentation.adapter.PlacesListAdapter
import com.tolulonge.weatherforecast.presentation.adapter.WindsListAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainWeatherFragment : Fragment() {

    private var _binding: FragmentMainWeatherBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var placesListAdapter: PlacesListAdapter
    private lateinit var windListAdapter: WindsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainWeatherBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placesListAdapter = PlacesListAdapter(places){
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.fragment_single_place_weather)
        }

        windListAdapter = WindsListAdapter(winds)


        binding.apply {
            layerDayCard.rvPlacesWeather.adapter = placesListAdapter
            layerNightCard.rvPlacesWeather.adapter = placesListAdapter
            layerNightCard.rvWindsWeather.adapter = windListAdapter
            layerDayCard.rvWindsWeather.adapter = windListAdapter
            layerNightCard.txtDayLayer.text = "Night"
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class Place(val name: String, val phenomenon: String, val temperature: String)
data class Wind(val name: String, val direction: String, val windSpeedRange: String)

val winds = listOf(Wind("Kussiku","East Wind","4.0 to 8.0"),
    Wind("Kussiku2","South Wind","4.0 to 8.2"),
    Wind("Kussiku2","West Wind","4.6 to 8.0"))

val places = listOf(Place(
    "São Paulo",
    "Heavy Rain",
    "24 degrees"
), Place(
    "Rio de Janeiro",
    "Moderate Shower",
    "56 degrees"
), Place(
    "Belo Horizonte",
    "Light Rain",
    "24 degrees"
), Place(
    "Curitiba",
    "Sunny",
    "35 degrees"
))