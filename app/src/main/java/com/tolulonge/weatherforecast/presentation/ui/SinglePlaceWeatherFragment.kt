package com.tolulonge.weatherforecast.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.core.util.loadGifs
import com.tolulonge.weatherforecast.core.util.loadHeaderGifs
import com.tolulonge.weatherforecast.databinding.FragmentSinglePlaceWeatherBinding
import com.tolulonge.weatherforecast.presentation.state.model.PresentationPlace


class SinglePlaceWeatherFragment : Fragment() {

    private var _binding: FragmentSinglePlaceWeatherBinding? = null
    private val args: SinglePlaceWeatherFragmentArgs by navArgs()

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

    private fun setUpViews(presentationPlace: PresentationPlace, date: String) {
        with(presentationPlace) {
            binding.apply {
                imgPhenomenonSinglePlace.loadGifs(phenomenon ?: "")
                ivSinglePlaceWeatherIcon.loadHeaderGifs(phenomenon ?: "")
                txtDate.text = date
                txtPlaceName.text = name ?: ""
                txtPlacePhenomenon.text = phenomenon ?: ""
                txtMinTemperature.text = getString(R.string.min_temp_single_place, tempmin ?: "_")
                txtMaxTemperature.text = getString(R.string.max_temp_single_place, tempmax ?: "_")
            }
        }

    }
}