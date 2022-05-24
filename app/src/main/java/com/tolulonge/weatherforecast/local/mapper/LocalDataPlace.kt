package com.tolulonge.weatherforecast.local.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ToAndFroListMapper
import com.tolulonge.weatherforecast.data.model.DataPlace
import com.tolulonge.weatherforecast.local.entities.LocalPlace

class LocalDataPlace: ToAndFroListMapper<LocalPlace, DataPlace> {

    override fun from(e: List<DataPlace>): List<LocalPlace> {
        return e.map { toLocal(it) }
    }

    override fun mTo(t: List<LocalPlace>): List<DataPlace> {
        return t.map { toData(it) }

    }

    private fun toData(from: LocalPlace): DataPlace {
        return DataPlace(
            name = from.name,
            phenomenon = from.phenomenon,
            tempmax = from.tempmax,
            tempmin = from.tempmin
        )
    }

    private fun toLocal(from: DataPlace): LocalPlace {
        return LocalPlace(
            name = from.name,
            phenomenon = from.phenomenon,
            tempmax = from.tempmax,
            tempmin = from.tempmin
        )
    }
}