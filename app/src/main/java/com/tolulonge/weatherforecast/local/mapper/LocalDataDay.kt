package com.tolulonge.weatherforecast.local.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ToAndFroMapper
import com.tolulonge.weatherforecast.data.model.DataDay
import com.tolulonge.weatherforecast.local.entities.LocalDay

class LocalDataDay(
    private val localDataPlace: LocalDataPlace,
    private val localDataWind: LocalDataWind
) : ToAndFroMapper<LocalDay, DataDay> {
    override fun from(e: DataDay): LocalDay {
        return toLocal(e)
    }

    override fun mTo(t: LocalDay): DataDay {
        return toData(t)
    }

    private fun toData(from: LocalDay): DataDay {
        return DataDay(
            peipsi = from.peipsi,
            phenomenon = from.phenomenon,
            places = localDataPlace.mTo(from.places ?: emptyList()),
            sea = from.sea,
            tempmax = from.tempmax,
            tempmin = from.tempmin,
            text = from.text,
            winds = localDataWind.mTo(from.winds ?: emptyList())
        )
    }

    private fun toLocal(from: DataDay): LocalDay {
        return LocalDay(
            peipsi = from.peipsi,
            phenomenon = from.phenomenon,
            places = localDataPlace.from(from.places ?: emptyList()),
            sea = from.sea,
            tempmax = from.tempmax,
            tempmin = from.tempmin,
            text = from.text,
            winds = localDataWind.from(from.winds ?: emptyList())
        )
    }
}