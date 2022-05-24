package com.tolulonge.weatherforecast.domain.mapper

import com.tolulonge.weatherforecast.core.util.mapper.Mapper
import com.tolulonge.weatherforecast.data.model.DataDay
import com.tolulonge.weatherforecast.domain.model.DomainDay

class DataDayToDomainDay(
    private val dataPlaceToDomainPlace: DataPlaceToDomainPlace,
    private val dataWindToDomainWind: DataWindToDomainWind
    ): Mapper<DataDay, DomainDay> {
    override fun map(input: DataDay): DomainDay = with(input){
        DomainDay(
            peipsi = peipsi,
            phenomenon = phenomenon,
            places =  dataPlaceToDomainPlace.map(places ?: emptyList()),
            sea = sea,
            tempmax = tempmax,
            tempmin = tempmin,
            text = text,
            winds = dataWindToDomainWind.map(winds ?: emptyList())
        )
    }

}