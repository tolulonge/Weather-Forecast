package com.tolulonge.weatherforecast.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.core.util.hide
import com.tolulonge.weatherforecast.core.util.show
import com.tolulonge.weatherforecast.core.util.showSnackBar
import com.tolulonge.weatherforecast.core.util.showSnackBarWithAction
import com.tolulonge.weatherforecast.databinding.FragmentMainWeatherBinding
import com.tolulonge.weatherforecast.presentation.adapter.PlacesListAdapter
import com.tolulonge.weatherforecast.presentation.adapter.WindsListAdapter
import com.tolulonge.weatherforecast.presentation.event.WeatherForecastEvent
import com.tolulonge.weatherforecast.presentation.state.MainWeatherFragmentUiState
import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecast
import com.tolulonge.weatherforecast.presentation.state.model.toPresentationForecastGallery
import com.tolulonge.weatherforecast.presentation.viewmodel.MainWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainWeatherFragment : Fragment() {

    private var _binding: FragmentMainWeatherBinding? = null

    private val binding get() = _binding!!
    private lateinit var placesListDayAdapter: PlacesListAdapter
    private lateinit var placesListNightAdapter: PlacesListAdapter
    private lateinit var windsListDayAdapter: WindsListAdapter
    private lateinit var windsListNightAdapter: WindsListAdapter
    private var allWeatherForecast: List<PresentationForecast>? = null
    private val mainWeatherViewModel: MainWeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainWeatherBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setUpRecyclerView()
        subscribeToObservables()


        binding.imgSwitchDay.setOnClickListener {
            // Navigate to gallery and fetch all weather forecasts
            allWeatherForecast?.let { list ->
                val action = MainWeatherFragmentDirections.actionFragmentMainWeatherToForecastDaysGallery(
                     list.map{it.toPresentationForecastGallery()}.toTypedArray()
                )
                findNavController().navigate(action)
            }
        }

        binding.noDataTextView.setOnClickListener {
            reloadWeatherForecasts()
        }


        placesListDayAdapter.setOnItemClickListener {
            val todayWeather = allWeatherForecast?.get(0) ?:  return@setOnItemClickListener

            val action = MainWeatherFragmentDirections.actionFragmentMainWeatherToFragmentSinglePlaceWeather(
                todayWeather.date!!,it
            )
            findNavController().navigate(action)
        }

        placesListNightAdapter.setOnItemClickListener {
            val todayWeather = allWeatherForecast?.get(0) ?:  return@setOnItemClickListener

            val action = MainWeatherFragmentDirections.actionFragmentMainWeatherToFragmentSinglePlaceWeather(
                todayWeather.date!!,it
            )
            findNavController().navigate(action)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            reloadWeatherForecasts()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_weathers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reload_weather -> {
                binding.swipeRefreshLayout.isRefreshing = true
                reloadWeatherForecasts()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpRecyclerView() {
        placesListDayAdapter = PlacesListAdapter()
        placesListNightAdapter = PlacesListAdapter()
        windsListDayAdapter = WindsListAdapter()
        windsListNightAdapter = WindsListAdapter()
        binding.apply {
            layerNightCard.txtDayLayer.text = "Night"
            layerDayCard.rvPlacesWeather.adapter = placesListDayAdapter
            layerNightCard.rvPlacesWeather.adapter = placesListNightAdapter
            layerNightCard.rvWindsWeather.adapter = windsListDayAdapter
            layerDayCard.rvWindsWeather.adapter = windsListNightAdapter
        }
    }

    private fun setUpViews(presentationForecast: PresentationForecast) {
        with(presentationForecast){
            binding.txtDate.text = presentationForecast.date

            binding.layerDayCard.apply {
                txtTemperatureRange.text = "${day?.tempmin ?: "nil"} to ${day?.tempmax ?: "nil"} degrees"
                txtPhenomenon.text = day?.phenomenon ?: "No data"
                txtMainDescription.text = day?.text ?: "No data"
                txtSeaDescription.text = day?.sea ?: "No data"
                txtPeipsiInfo.text = day?.peipsi ?: "No data"
            }
        }
        with(presentationForecast){
            binding.layerNightCard.apply {
                txtTemperatureRange.text = "${night?.tempmin ?: "nil"} to ${night?.tempmax ?: "nil"} degrees"
                txtPhenomenon.text = night?.phenomenon ?: "No data"
                txtMainDescription.text = night?.text ?: "No data"
                txtSeaDescription.text = night?.sea ?: "No data"
                txtPeipsiInfo.text = night?.peipsi ?: "No data"
            }
        }
    }


    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {

            mainWeatherViewModel.allWeatherForecast.collectLatest { state ->
                handleDataAndEmptyScenarios(state.isNotEmpty())
                    if (state.isNotEmpty()){
                        placesListDayAdapter.differ.submitList(state[0].day?.places)
                        placesListNightAdapter.differ.submitList(state[0].night?.places)
                        windsListDayAdapter.differ.submitList(state[0].day?.winds)
                        windsListNightAdapter.differ.submitList(state[0].night?.winds)
                        allWeatherForecast = state
                        setUpViews(state[0])
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            mainWeatherViewModel.remoteUpdateResponse.collectLatest {state ->
                when(state){
                    MainWeatherFragmentUiState.Empty -> {
                    }
                    is MainWeatherFragmentUiState.Error -> {
                        if (state.message.isNotEmpty())
                            binding.root.showSnackBarWithAction(state.message, "Retry") {
                                reloadWeatherForecasts()
                            }
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                    is MainWeatherFragmentUiState.Loaded -> {
                      if(state.data.isNotEmpty())
                          binding.root.showSnackBar(state.data)
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                    is MainWeatherFragmentUiState.Loading -> {
                        if (state.isLoading) {
                            binding.apply{
                                layerDayCard.progressBarLayer.show()
                                layerNightCard.progressBarLayer.show()
                            }
                            if (state.message.isNotEmpty())
                            binding.root.showSnackBar(state.message)

                        } else {
                            binding.apply{
                                layerDayCard.progressBarLayer.hide()
                                layerNightCard.progressBarLayer.hide()
                            }
                        }
                    }
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

    private fun reloadWeatherForecasts(){
        mainWeatherViewModel.onEvent(WeatherForecastEvent.RefreshWeatherForecast)
    }

}

