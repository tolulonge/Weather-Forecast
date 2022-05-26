package com.tolulonge.weatherforecast.presentation.mapper

import com.tolulonge.weatherforecast.core.util.mapper.Mapper
import com.tolulonge.weatherforecast.domain.model.DomainNight
import com.tolulonge.weatherforecast.presentation.state.model.PresentationNight

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
            tempmax = tempmax?.toString(),
            tempmin = tempmin?.toString(),
            text = text,
            winds = domainWindToPresentationWind.map(winds ?: emptyList())
        )
    }

}