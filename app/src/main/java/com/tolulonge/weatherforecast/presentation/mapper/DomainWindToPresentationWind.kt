package com.tolulonge.weatherforecast.presentation.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.domain.model.DomainWind
import com.tolulonge.weatherforecast.presentation.state.model.PresentationWind

class DomainWindToPresentationWind: ListMapper<DomainWind, PresentationWind> {
    override fun map(input: List<DomainWind>): List<PresentationWind> = with(input){
        map {
            PresentationWind(
                name = it.name,
                direction = it.direction,
                speedmax = it.speedmax,
                speedmin = it.speedmin
            )
        }
    }
}