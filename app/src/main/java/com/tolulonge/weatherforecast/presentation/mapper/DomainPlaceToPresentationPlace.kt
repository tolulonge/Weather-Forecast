package com.tolulonge.weatherforecast.presentation.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.domain.model.DomainPlace
import com.tolulonge.weatherforecast.presentation.state.model.PresentationPlace

class DomainPlaceToPresentationPlace: ListMapper<DomainPlace, PresentationPlace> {
    override fun map(input: List<DomainPlace>): List<PresentationPlace> = with(input){
        map {
            PresentationPlace(
                name = it.name,
                phenomenon = it.phenomenon,
                tempmax = it.tempmax?.toString(),
                tempmin = it.tempmin?.toString(),
            )
        }
    }
}