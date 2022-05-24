package com.tolulonge.weatherforecast.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.databinding.FragmentForecastDaysGalleryBinding
import com.tolulonge.weatherforecast.presentation.adapter.ForecastDaysAdapter
import com.tolulonge.weatherforecast.presentation.adapter.PlacesListAdapter
import com.tolulonge.weatherforecast.presentation.adapter.WindsListAdapter


class ForecastDaysGalleryFragment : Fragment() {

    private var _binding: FragmentForecastDaysGalleryBinding? = null
    private val args: ForecastDaysGalleryFragmentArgs by navArgs()
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
        setUpRecyclerView()

        forecastDaysAdapter.differ.submitList(args.forecastDaysGallery.toList())
        forecastDaysAdapter.setOnItemClickListener {
            val action = ForecastDaysGalleryFragmentDirections.actionForecastDaysGalleryToSpecificDayWeatherFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {
        forecastDaysAdapter = ForecastDaysAdapter()
        binding.rvForecastDaysGallery.adapter = forecastDaysAdapter
    }

}
