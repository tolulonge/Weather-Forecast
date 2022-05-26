package com.tolulonge.weatherforecast.local.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ToAndFroListMapper
import com.tolulonge.weatherforecast.data.model.DataWind
import com.tolulonge.weatherforecast.local.entities.LocalWind

class LocalDataWind: ToAndFroListMapper<LocalWind, DataWind> {

    override fun from(e: List<DataWind>): List<LocalWind> {
        return e.map { toLocal(it) }
    }

    override fun mTo(t: List<LocalWind>): List<DataWind> {
        return t.map { toData(it) }

    }

    private fun toData(from: LocalWind): DataWind {
        return DataWind(
            name = from.name,
            direction = from.direction,
            speedmax = from.speedmax,
            speedmin = from.speedmin
        )
    }

    private fun toLocal(from: DataWind): LocalWind {
        return LocalWind(
            name = from.name,
            direction = from.direction,
            speedmax = from.speedmax,
            speedmin = from.speedmin
        )
    }
}