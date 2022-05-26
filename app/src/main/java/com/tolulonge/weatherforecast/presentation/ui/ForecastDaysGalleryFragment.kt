package com.tolulonge.weatherforecast.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.core.util.hide
import com.tolulonge.weatherforecast.core.util.show
import com.tolulonge.weatherforecast.core.util.showSnackBar
import com.tolulonge.weatherforecast.core.util.showSnackBarWithAction
import com.tolulonge.weatherforecast.databinding.FragmentForecastDaysGalleryBinding
import com.tolulonge.weatherforecast.presentation.adapter.ForecastDaysAdapter
import com.tolulonge.weatherforecast.presentation.event.WeatherForecastEvent
import com.tolulonge.weatherforecast.presentation.state.MainWeatherFragmentUiState
import com.tolulonge.weatherforecast.presentation.state.model.toPresentationForecastGallery
import com.tolulonge.weatherforecast.presentation.viewmodel.WeatherForecastViewModel
import kotlinx.coroutines.flow.collectLatest


class ForecastDaysGalleryFragment : Fragment() {

    private var _binding: FragmentForecastDaysGalleryBinding? = null
    private val binding get() = _binding!!
    private val weatherForecastViewModel: WeatherForecastViewModel by activityViewModels()
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
        subscribeToObservables()

        forecastDaysAdapter.setOnItemClickListener {date, position ->
            val action = ForecastDaysGalleryFragmentDirections.actionForecastDaysGalleryToSpecificWeatherFragment(date)
            if (position == 0) {
                findNavController().navigate(R.id.action_forecastDaysGallery_to_fragment_main_weather)
            } else {
                findNavController().navigate(action)
            }
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

    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {

            weatherForecastViewModel.allWeatherForecast.collectLatest { state ->
                handleDataAndEmptyScenarios(state.isNotEmpty())
                if (state.isNotEmpty()){
                    forecastDaysAdapter.differ.submitList(state.map { it.toPresentationForecastGallery() })
                }
            }
        }

    }

    private fun handleDataAndEmptyScenarios(isAvailable: Boolean) {
        if(isAvailable){
            binding.apply {
                noDataImageView.hide()
                noDataTextView.hide()
            }
        }else{
            binding.apply {
                noDataImageView.show()
                noDataTextView.show()
            }
        }
    }


}
