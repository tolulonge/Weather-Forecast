package com.tolulonge.weatherforecast.remote.mapper

import com.tolulonge.weatherforecast.core.util.mapper.Mapper
import com.tolulonge.weatherforecast.data.model.DataNight
import com.tolulonge.weatherforecast.remote.model.weatherforecast.Night

class RemoteNightToDataNight(
    private val remotePlaceToDataPlace: RemotePlaceToDataPlace,
    private val remoteWindToDataWind: RemoteWindToDataWind
    ): Mapper<Night, DataNight> {
    override fun map(input: Night): DataNight = with(input){
        DataNight(
            peipsi = peipsi,
            phenomenon = phenomenon,
            places =  remotePlaceToDataPlace.map(places ?: emptyList()),
            sea = sea,
            tempmax = tempmax,
            tempmin = tempmin,
            text = text,
            winds = remoteWindToDataWind.map(winds ?: emptyList())
        )
    }

}