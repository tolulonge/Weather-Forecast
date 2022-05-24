package com.tolulonge.weatherforecast.remote.mapper

import com.tolulonge.weatherforecast.core.util.mapper.Mapper
import com.tolulonge.weatherforecast.data.model.DataDay
import com.tolulonge.weatherforecast.remote.model.weatherforecast.Day

class RemoteDayToDataDay(
    private val remotePlaceToDataPlace: RemotePlaceToDataPlace,
    private val remoteWindToDataWind: RemoteWindToDataWind
    ): Mapper<Day, DataDay> {
    override fun map(input: Day): DataDay = with(input){
        DataDay(
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