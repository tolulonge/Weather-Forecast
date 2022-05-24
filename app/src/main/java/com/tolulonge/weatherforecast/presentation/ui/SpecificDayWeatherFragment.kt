package com.tolulonge.weatherforecast.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.core.util.hide
import com.tolulonge.weatherforecast.databinding.FragmentMainWeatherBinding
import com.tolulonge.weatherforecast.databinding.FragmentSinglePlaceWeatherBinding
import com.tolulonge.weatherforecast.databinding.FragmentSpecificDayWeatherBinding
import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecast
import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecastGallery
import com.tolulonge.weatherforecast.presentation.viewmodel.MainWeatherViewModel

class SpecificDayWeatherFragment : Fragment() {
    private var _binding: FragmentSpecificDayWeatherBinding? = null

    private val binding get() = _binding!!
    private val args: SpecificDayWeatherFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSpecificDayWeatherBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(args.specificDay)

        binding.imgSwitchDay.setOnClickListener {
                findNavController().navigate(R.id.forecastDaysGallery)
            }
        }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpViews(forecast: PresentationForecastGallery) {
        with(forecast){
            binding.layerDayCard.apply {
                txtTemperatureRange.text = "${dTempmin}"
                txtPhenomenon.text = dPhenomenon ?: ""
                txtMainDescription.text = dText ?: ""
                txtSeaDescription.text = dSea ?: ""
                txtPeipsiInfo.text = dPeipsi ?: ""
                rvPlacesWeather.hide()
                rvWindsWeather.hide()
            }
        }
        with(forecast){
            binding.layerDayCard.apply {
                txtTemperatureRange.text = "$nTempmin"
                txtPhenomenon.text = nPhenomenon ?: ""
                txtMainDescription.text = nText ?: ""
                txtSeaDescription.text = nSea ?: ""
                txtPeipsiInfo.text = nPeipsi ?: ""
                rvPlacesWeather.hide()
                rvWindsWeather.hide()
            }
        }
    }
}