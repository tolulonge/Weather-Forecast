package com.tolulonge.weatherforecast.remote.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.data.model.DataWind
import com.tolulonge.weatherforecast.remote.model.weatherforecast.Wind

class RemoteWindToDataWind: ListMapper<Wind, DataWind> {
    override fun map(input: List<Wind>): List<DataWind> = with(input){
        map {
            DataWind(
                name = it.name,
                direction = it.direction,
                speedmax = it.speedmax,
                speedmin = it.speedmin
            )
        }
    }
}