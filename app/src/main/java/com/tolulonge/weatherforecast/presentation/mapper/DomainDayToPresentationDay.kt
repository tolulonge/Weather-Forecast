package com.tolulonge.weatherforecast.presentation.mapper

import com.tolulonge.weatherforecast.core.util.mapper.Mapper
import com.tolulonge.weatherforecast.data.model.DataDay
import com.tolulonge.weatherforecast.domain.model.DomainDay
import com.tolulonge.weatherforecast.presentation.state.model.PresentationDay
import com.tolulonge.weatherforecast.presentation.state.model.PresentationPlace
import com.tolulonge.weatherforecast.presentation.state.model.PresentationWind
import com.tolulonge.weatherforecast.remote.model.weatherforecast.Day

class DomainDayToPresentationDay(
    private val domainPlaceToPresentationPlace: DomainPlaceToPresentationPlace,
    private val domainWindTOPresentationWind: DomainWindToPresentationWind
    ): Mapper<DomainDay, PresentationDay> {
    override fun map(input: DomainDay): PresentationDay = with(input){
        PresentationDay(
            peipsi = peipsi,
            phenomenon = phenomenon,
            places =  domainPlaceToPresentationPlace.map(places ?: emptyList()),
            sea = sea,
            tempmax = tempmax?.toString(),
            tempmin = tempmin?.toString(),
            text = text,
            winds = domainWindTOPresentationWind.map(winds ?: emptyList())
        )
    }

}