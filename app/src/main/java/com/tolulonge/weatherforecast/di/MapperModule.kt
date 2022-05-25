package com.tolulonge.weatherforecast.di

import com.tolulonge.weatherforecast.domain.mapper.DataDayToDomainDay
import com.tolulonge.weatherforecast.domain.mapper.DataNightToDomainNight
import com.tolulonge.weatherforecast.domain.mapper.DataPlaceToDomainPlace
import com.tolulonge.weatherforecast.domain.mapper.DataWindToDomainWind
import com.tolulonge.weatherforecast.local.mapper.LocalDataDay
import com.tolulonge.weatherforecast.local.mapper.LocalDataNight
import com.tolulonge.weatherforecast.local.mapper.LocalDataPlace
import com.tolulonge.weatherforecast.local.mapper.LocalDataWind
import com.tolulonge.weatherforecast.presentation.mapper.*
import com.tolulonge.weatherforecast.remote.mapper.RemoteDayToDataDay
import com.tolulonge.weatherforecast.remote.mapper.RemoteNightToDataNight
import com.tolulonge.weatherforecast.remote.mapper.RemotePlaceToDataPlace
import com.tolulonge.weatherforecast.remote.mapper.RemoteWindToDataWind
import dagger.Provides
import javax.inject.Singleton

object MapperModule {

    @Provides
    @Singleton
    fun provideRemoteDayToDataDayMapper(): RemoteDayToDataDay {
        return RemoteDayToDataDay(
            remotePlaceToDataPlace = RemotePlaceToDataPlace(),
            remoteWindToDataWind = RemoteWindToDataWind()
        )
    }

    @Provides
    @Singleton
    fun provideRemoteNightToDataNightMapper(): RemoteNightToDataNight {
        return RemoteNightToDataNight(
            remotePlaceToDataPlace = RemotePlaceToDataPlace(),
            remoteWindToDataWind = RemoteWindToDataWind()
        )
    }

    @Provides
    @Singleton
    fun provideLocalDataDayMapper(): LocalDataDay {
        return LocalDataDay(
            localDataPlace = LocalDataPlace(),
            localDataWind = LocalDataWind()
        )
    }

    @Provides
    @Singleton
    fun provideLocalDataNightMapper(): LocalDataNight {
        return LocalDataNight(
            localDataPlace = LocalDataPlace(),
            localDataWind = LocalDataWind()
        )
    }

    @Provides
    @Singleton
    fun providesDataDayToDomainDayMapper(): DataDayToDomainDay {
        return DataDayToDomainDay(
            dataPlaceToDomainPlace = DataPlaceToDomainPlace(),
            dataWindToDomainWind = DataWindToDomainWind()
        )
    }

    @Provides
    @Singleton
    fun providesDataNightToDomainNightMapper(): DataNightToDomainNight {
        return DataNightToDomainNight(
            dataPlaceToDomainPlace = DataPlaceToDomainPlace(),
            dataWindToDomainWind = DataWindToDomainWind()
        )
    }

    @Provides
    @Singleton
    fun providesDomainForecastToPresentationForecastMapper(
        domainDayToPresentationDay: DomainDayToPresentationDay,
        domainNightToPresentationNight: DomainNightToPresentationNight
    ): DomainForecastToPresentationForecastMapper {
        return DomainForecastToPresentationForecastMapper(
            domainDayToPresentationDay, domainNightToPresentationNight
        )
    }

    @Provides
    @Singleton
    fun providesDomainDayToPresentationDayMapper(): DomainDayToPresentationDay {
        return DomainDayToPresentationDay(
            domainPlaceToPresentationPlace = DomainPlaceToPresentationPlace(),
            domainWindTOPresentationWind = DomainWindToPresentationWind()
        )
    }

    @Provides
    @Singleton
    fun providesDomainNightToPresentationNightMapper(
    ): DomainNightToPresentationNight {
        return DomainNightToPresentationNight(
            domainPlaceToPresentationPlace = DomainPlaceToPresentationPlace(),
            domainWindToPresentationWind = DomainWindToPresentationWind()
        )
    }

    @Provides
    @Singleton
    fun providesSingleDomainForecastToPresentationForecastMapper(
        domainDayToPresentationDay: DomainDayToPresentationDay,
        domainNightToPresentationNight: DomainNightToPresentationNight
    ): SingleDomainForecastToPresentationForecastMapper {
        return SingleDomainForecastToPresentationForecastMapper(
            domainDayToPresentationDay, domainNightToPresentationNight
        )
    }
}