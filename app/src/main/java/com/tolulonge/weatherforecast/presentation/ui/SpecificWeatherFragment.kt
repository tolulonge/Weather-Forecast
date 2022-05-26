package com.tolulonge.weatherforecast.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.core.util.*
import com.tolulonge.weatherforecast.databinding.FragmentSpecificWeatherBinding
import com.tolulonge.weatherforecast.presentation.event.WeatherForecastEvent
import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecast
import com.tolulonge.weatherforecast.presentation.viewmodel.SpecificWeatherForecastViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@AndroidEntryPoint
class SpecificWeatherFragment : Fragment() {

    private var _binding: FragmentSpecificWeatherBinding? = null
    private val args: SpecificWeatherFragmentArgs by navArgs()
    private val binding get() = _binding!!
    private val specificWeatherForecastViewModel: SpecificWeatherForecastViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSpecificWeatherBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservables()


        specificWeatherForecastViewModel.onEvent(WeatherForecastEvent.GetForecastByDate(args.weatherDate))
        binding.imgSwitchDay.setOnClickListener {
            findNavController().navigate(R.id.action_specificWeatherFragment_to_forecastDaysGallery)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {
            specificWeatherForecastViewModel.specificDayWeather.collectLatest { state ->
                        setUpViews(state)
                }
            }
        }

    private fun setUpViews(presentationForecast: PresentationForecast) {
        binding.apply {
            overallWeatherDescGif.loadHeaderGifs(presentationForecast.day?.phenomenon ?: "")
            txtDate.text = presentationForecast.date
            imgSwitchDay.loadGifs(R.drawable.calendar)
            txtToday.text = ""
        }

        with(presentationForecast){
            binding.layerDayCard.apply {
                txtTemperatureRange.text =     "${day?.tempmin ?: "nil"} to ${day?.tempmax ?: "nil"} degrees"
                txtPhenomenon.text = day?.phenomenon ?: "No data"
                txtMainDescription.text = day?.text ?: "No data"
                imgPhenomenon.loadGifs(day?.phenomenon?.lowercase(Locale.getDefault()) ?: "")
                imgMainDescription.loadGifs(R.drawable.description)
                imgTemperature.loadGifs(R.drawable.temperature)
            }
        }
        with(presentationForecast){
            binding.layerNightCard.apply {
                txtDayLayer.text = "Night"
                txtTemperatureRange.text = "${night?.tempmin ?: "nil"} to ${night?.tempmax ?: "nil"} degrees"
                txtPhenomenon.text = night?.phenomenon ?: "No data"
                txtMainDescription.text = night?.text ?: "No data"
                imgPhenomenon.loadGifs(night?.phenomenon?.lowercase(Locale.getDefault()) ?: "")
                imgMainDescription.loadGifs(R.drawable.description)
                imgTemperature.loadGifs(R.drawable.temperature)
            }
        }
    }

}

