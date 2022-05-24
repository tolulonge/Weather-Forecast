package com.tolulonge.weatherforecast.remote.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.data.model.DataPlace
import com.tolulonge.weatherforecast.remote.model.weatherforecast.Place

class RemotePlaceToDataPlace: ListMapper<Place, DataPlace> {
    override fun map(input: List<Place>): List<DataPlace> = with(input){
        map {
            DataPlace(
                name = it.name,
                phenomenon = it.phenomenon,
                tempmax = it.tempmax,
                tempmin = it.tempmin,
            )
        }
    }
}