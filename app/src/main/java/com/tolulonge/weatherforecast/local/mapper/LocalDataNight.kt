package com.tolulonge.weatherforecast.local.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ToAndFroMapper
import com.tolulonge.weatherforecast.data.model.DataNight
import com.tolulonge.weatherforecast.local.entities.LocalNight

class LocalDataNight(
    private val localDataPlace: LocalDataPlace,
    private val localDataWind: LocalDataWind
) : ToAndFroMapper<LocalNight, DataNight> {
    override fun from(e: DataNight): LocalNight {
        return toLocal(e)
    }

    override fun mTo(t: LocalNight): DataNight {
        return toData(t)
    }

    private fun toData(from: LocalNight): DataNight {
        return DataNight(
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

    private fun toLocal(from: DataNight): LocalNight {
        return LocalNight(
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