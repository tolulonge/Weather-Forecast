package com.tolulonge.weatherforecast.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tolulonge.weatherforecast.R
import com.tolulonge.weatherforecast.core.util.*
import com.tolulonge.weatherforecast.databinding.FragmentMainWeatherBinding
import com.tolulonge.weatherforecast.presentation.adapter.PlacesListAdapter
import com.tolulonge.weatherforecast.presentation.adapter.WindsListAdapter
import com.tolulonge.weatherforecast.presentation.event.WeatherForecastEvent
import com.tolulonge.weatherforecast.presentation.state.MainWeatherFragmentUiState
import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecast
import com.tolulonge.weatherforecast.presentation.state.model.PresentationPlace
import com.tolulonge.weatherforecast.presentation.viewmodel.WeatherForecastViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@AndroidEntryPoint
class MainWeatherFragment : Fragment() {

    private var _binding: FragmentMainWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var placesListDayAdapter: PlacesListAdapter
    private lateinit var placesListNightAdapter: PlacesListAdapter
    private lateinit var windsListDayAdapter: WindsListAdapter
    private lateinit var windsListNightAdapter: WindsListAdapter
    private var allWeatherForecast: List<PresentationForecast>? = null
    private val weatherForecastViewModel: WeatherForecastViewModel by activityViewModels()

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
            findNavController().navigate(R.id.action_fragment_main_weather_to_forecastDaysGallery)
        }


        binding.noDataTextView.setOnClickListener {
            reloadWeatherForecasts()
        }


        placesListDayAdapter.setOnItemClickListener {presentationPlace ->
            navigateToPlaceDetails(presentationPlace)
        }

        placesListNightAdapter.setOnItemClickListener {presentationPlace ->
            navigateToPlaceDetails(presentationPlace)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            reloadWeatherForecasts()
        }


    }

    private fun navigateToPlaceDetails(presentationPlace: PresentationPlace) {
        val todayWeather = allWeatherForecast?.get(0) ?: return

        todayWeather.date?.let {
            val action =
                MainWeatherFragmentDirections.actionFragmentMainWeatherToFragmentSinglePlaceWeather(
                    it, presentationPlace, presentationPlace.name ?: "Place Forecast"
                )
            findNavController().navigate(action)
        } ?: run {
            binding.root.showSnackBar(getString(R.string.no_data_available))
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
        binding.overallWeatherDescGif.loadHeaderGifs(presentationForecast.day?.phenomenon ?: "")
        binding.txtDate.text = presentationForecast.date
        binding.imgSwitchDay.loadGifs(R.drawable.calendar)

        with(presentationForecast){
            binding.layerDayCard.apply {
                txtTemperatureRange.text = "${day?.tempmin ?: "nil"} to ${day?.tempmax ?: "nil"} degrees"
                txtPhenomenon.text = day?.phenomenon ?: "No data"
                txtMainDescription.text = day?.text ?: "No data"
                txtSeaDescription.text = day?.sea ?: "No data"
                txtPeipsiInfo.text = day?.peipsi ?: "No data"
                if (day?.places.isNullOrEmpty()) txtPlacesDesc.text = "Places: No Data"
                if (day?.winds.isNullOrEmpty()) txtWindsDesc.text = "Winds: No Data"
                imgPhenomenon.loadGifs(day?.phenomenon?.lowercase(Locale.getDefault()) ?: "")
                imgMainDescription.loadGifs(R.drawable.description)
                imgSeaDescription.loadGifs(R.drawable.tide)
                imgPeipsiInfo.loadGifs(R.drawable.signposts)
                imgTemperature.loadGifs(R.drawable.temperature)
            }
        }
        with(presentationForecast){
            binding.layerNightCard.apply {
                txtTemperatureRange.text = "${night?.tempmin ?: "nil"} to ${night?.tempmax ?: "nil"} degrees"
                txtPhenomenon.text = night?.phenomenon ?: "No data"
                txtMainDescription.text = night?.text ?: "No data"
                txtSeaDescription.text = night?.sea ?: "No data"
                txtPeipsiInfo.text = night?.peipsi ?: "No data"
                if (night?.places.isNullOrEmpty()) txtPlacesDesc.text = "Places: No Data"
                if (night?.winds.isNullOrEmpty()) txtWindsDesc.text = "Winds: No Data"
                imgPhenomenon.loadGifs(night?.phenomenon?.lowercase(Locale.getDefault()) ?: "")
                imgMainDescription.loadGifs(R.drawable.description)
                imgSeaDescription.loadGifs(R.drawable.tide)
                imgPeipsiInfo.loadGifs(R.drawable.signposts)
                imgTemperature.loadGifs(R.drawable.temperature)
            }
        }
    }


    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {

            weatherForecastViewModel.allWeatherForecast.collectLatest { state ->
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
            weatherForecastViewModel.remoteUpdateResponse.collectLatest { state ->
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
        weatherForecastViewModel.onEvent(WeatherForecastEvent.RefreshWeatherForecast)
    }

}

