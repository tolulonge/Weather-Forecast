package com.tolulonge.weatherforecast.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_forecast_table")
data class LocalForecast(
    @PrimaryKey
    @ColumnInfo(name = "weather_forecast_date")
    val date: String,
    val day: LocalDay,
    val night: LocalNight
)