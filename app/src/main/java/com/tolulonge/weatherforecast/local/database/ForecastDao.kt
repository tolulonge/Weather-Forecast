package com.tolulonge.weatherforecast.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolulonge.weatherforecast.local.entities.LocalForecast

@Dao
interface ForecastDao {

    @Query("SELECT * FROM weather_forecast_table ORDER BY weather_forecast_date ASC")
    suspend fun getAllWeatherForecasts(): List<LocalForecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWeatherForecasts(allWeatherForecasts: List<LocalForecast>)

    @Query("SELECT * FROM weather_forecast_table WHERE weather_forecast_date = :date")
    suspend fun getWeatherForecastByDate(date: String): LocalForecast

}
