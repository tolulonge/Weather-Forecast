package com.tolulonge.weatherforecast.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tolulonge.weatherforecast.databinding.FragmentSinglePlaceWeatherBinding
import com.tolulonge.weatherforecast.presentation.state.model.PresentationPlace


class SinglePlaceWeatherFragment : Fragment() {

    private var _binding: FragmentSinglePlaceWeatherBinding? = null
    private val args : SinglePlaceWeatherFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSinglePlaceWeatherBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(args.placeDetails, args.weatherDate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpViews(presentationPlace: PresentationPlace, date: String){
        with(presentationPlace){
            binding.apply {
                txtDate.text = date
                txtPlaceName.text = name ?: ""
                txtPlacePhenomenon.text = phenomenon ?: ""
                txtMinTemperature.text = (tempmin ?: "").toString()
                txtMaxTemperature.text = (tempmax ?: "").toString()
            }
        }

    }
}