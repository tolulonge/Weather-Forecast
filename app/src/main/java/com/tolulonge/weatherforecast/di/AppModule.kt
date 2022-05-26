package com.tolulonge.weatherforecast.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.tolulonge.weatherforecast.core.util.parser.GsonParser
import com.tolulonge.weatherforecast.data.repository.LocalDataSource
import com.tolulonge.weatherforecast.data.repository.RemoteDataSource
import com.tolulonge.weatherforecast.data.repository.WeatherForecastRepositoryImpl
import com.tolulonge.weatherforecast.domain.mapper.*
import com.tolulonge.weatherforecast.domain.repository.WeatherForecastRepository
import com.tolulonge.weatherforecast.local.converter.Converters
import com.tolulonge.weatherforecast.local.database.WeatherForecastDatabase
import com.tolulonge.weatherforecast.local.mapper.*
import com.tolulonge.weatherforecast.local.source.LocalDataSourceImpl
import com.tolulonge.weatherforecast.remote.api.WeatherForecastApi
import com.tolulonge.weatherforecast.remote.mapper.*
import com.tolulonge.weatherforecast.remote.source.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideWeatherForecastApi(): WeatherForecastApi {

        val client = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
        return Retrofit.Builder()
            .baseUrl(WeatherForecastApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideConverter(
        gsonParser: GsonParser
    ): Converters {
        return Converters(gsonParser)
    }

    @Provides
    @Singleton
    fun providesGsonParser(
        gson: Gson
    ): GsonParser {
        return GsonParser(gson)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideWeatherForecastDatabase(
        app: Application,
        converter: Converters
    ): WeatherForecastDatabase {
        return Room.databaseBuilder(
            app,
            WeatherForecastDatabase::class.java,
            WeatherForecastDatabase.DATABASE_NAME
        ).addTypeConverter(converter).build()
    }

    @Provides
    @Singleton
    fun provideWeatherForecastRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        dataDayToDomainDay: DataDayToDomainDay,
        dataNightToDomainNight: DataNightToDomainNight
    ): WeatherForecastRepository {
        return WeatherForecastRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            dataForecastToDomainForecastMapper = DataForecastToDomainForecastMapper(
                dataDayToDomainDay, dataNightToDomainNight
            ),
            singleDataForecastToDomainForecastMapper = SingleDataForecastToDomainForecastMapper(
                dataDayToDomainDay, dataNightToDomainNight
            )
        )
    }

    @Provides
    @Singleton
    fun providesLocalDataSource(
        db: WeatherForecastDatabase,
        localDataDay: LocalDataDay,
        localDataNight: LocalDataNight
    ): LocalDataSource {
        return LocalDataSourceImpl(
            localDataForecastListMapper = LocalDataForecastListMapper(localDataDay, localDataNight),
            singleDataForecastToLocalForecastMapper = SingleDataForecastToLocalForecastMapper(
                localDataDay,
                localDataNight
            ),
            forecastDao = db.forecastDao
        )
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        weatherForecastApi: WeatherForecastApi,
        remoteDayToDataDay: RemoteDayToDataDay,
        remoteNightToDataNight: RemoteNightToDataNight
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            weatherForecastApi = weatherForecastApi,
            remoteForecastToDataForecastMapper = RemoteForecastToDataForecastMapper(
                remoteDayToDataDay, remoteNightToDataNight
            )
        )
    }

}
