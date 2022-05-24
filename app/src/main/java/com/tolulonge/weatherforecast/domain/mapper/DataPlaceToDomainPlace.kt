package com.tolulonge.weatherforecast.domain.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.data.model.DataPlace
import com.tolulonge.weatherforecast.domain.model.DomainPlace

class DataPlaceToDomainPlace: ListMapper<DataPlace, DomainPlace> {
    override fun map(input: List<DataPlace>): List<DomainPlace> = with(input){
        map {
            DomainPlace(
                name = it.name,
                phenomenon = it.phenomenon,
                tempmax = it.tempmax,
                tempmin = it.tempmin,
            )
        }
    }
}