package com.tolulonge.weatherforecast.presentation.mapper

import com.tolulonge.weatherforecast.core.util.mapper.Mapper
import com.tolulonge.weatherforecast.data.model.DataNight
import com.tolulonge.weatherforecast.domain.model.DomainNight
import com.tolulonge.weatherforecast.presentation.state.model.PresentationDay
import com.tolulonge.weatherforecast.presentation.state.model.PresentationNight
import com.tolulonge.weatherforecast.remote.model.weatherforecast.Night

class DomainNightToPresentationNight(
    private val domainPlaceToPresentationPlace: DomainPlaceToPresentationPlace,
    private val domainWindToPresentationWind: DomainWindToPresentationWind
    ): Mapper<DomainNight, PresentationNight> {
    override fun map(input: DomainNight): PresentationNight = with(input){
        PresentationNight(
            peipsi = peipsi,
            phenomenon = phenomenon,
            places =  domainPlaceToPresentationPlace.map(places ?: emptyList()),
            sea = sea,
            tempmax = tempmax,
            tempmin = tempmin,
            text = text,
            winds = domainWindToPresentationWind.map(winds ?: emptyList())
        )
    }

}