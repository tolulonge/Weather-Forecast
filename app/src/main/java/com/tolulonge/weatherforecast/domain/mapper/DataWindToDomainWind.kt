package com.tolulonge.weatherforecast.domain.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.data.model.DataWind
import com.tolulonge.weatherforecast.domain.model.DomainWind

class DataWindToDomainWind: ListMapper<DataWind, DomainWind> {
    override fun map(input: List<DataWind>): List<DomainWind> = with(input){
        map {
            DomainWind(
                name = it.name,
                direction = it.direction,
                speedmax = it.speedmax,
                speedmin = it.speedmin
            )
        }
    }
}