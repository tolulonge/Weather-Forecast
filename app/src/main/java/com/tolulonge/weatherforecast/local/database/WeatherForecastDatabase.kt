package com.tolulonge.weatherforecast.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tolulonge.weatherforecast.local.converter.Converters
import com.tolulonge.weatherforecast.local.entities.LocalForecast

@Database(
    entities = [LocalForecast::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class WeatherForecastDatabase : RoomDatabase() {

    abstract val forecastDao: ForecastDao

    companion object {
        const val DATABASE_NAME = "weather_forecast_db"
    }
}
