package com.tolulonge.weatherforecast.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolulonge.weatherforecast.local.entities.LocalForecast
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {

    @Query("SELECT * FROM weather_forecast_table ORDER BY weather_forecast_date ASC")
    fun getAllWeatherForecasts(): Flow<List<LocalForecast>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWeatherForecasts(allWeatherForecasts: List<LocalForecast>)

    @Query("SELECT * FROM weather_forecast_table WHERE weather_forecast_date = :date")
    fun getWeatherForecastByDate(date: String): Flow<LocalForecast>

}
