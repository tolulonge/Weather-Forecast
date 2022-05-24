package com.tolulonge.weatherforecast.domain.mapper

import com.tolulonge.weatherforecast.core.util.mapper.Mapper
import com.tolulonge.weatherforecast.data.model.DataNight
import com.tolulonge.weatherforecast.domain.model.DomainNight

class DataNightToDomainNight(
    private val dataPlaceToDomainPlace: DataPlaceToDomainPlace,
    private val dataWindToDomainWind: DataWindToDomainWind
    ): Mapper<DataNight, DomainNight> {
    override fun map(input: DataNight): DomainNight = with(input){
        DomainNight(
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