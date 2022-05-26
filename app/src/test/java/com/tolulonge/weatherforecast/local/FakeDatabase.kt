package com.tolulonge.weatherforecast.local

import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.local.entities.*
import com.tolulonge.weatherforecast.remote.TestRemoteData


class FakeDatabase {

    private val listOfPlaces = listOf(
        LocalPlace("Parnu","Cloudy Weather",3.0,4.0),
        LocalPlace("Estonia","Moderate Shower",10.0,16.0),
    )

    private val listOfWinds = listOf(
        LocalWind("West wind","Kuusiku",3.0,4.0),
        LocalWind("Tartu","Moderate Shower",10.0,16.0),
    )
    private val day1 = LocalDay(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Cloudy Weather",
        tempmax = 22.0,
        tempmin = 10.0,
        peipsi = null,
        text = null,
        sea = "Oceanic"
    )
    private val night1 = LocalNight(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Light Shower",
        tempmax = 22.0,
        tempmin = 10.0,
        peipsi = null,
        text = null,
        sea = "Oceanic"
    )
    private val day2 = LocalDay(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Heavy Rain",
        tempmax = 22.0,
        tempmin = 10.0,
        peipsi = null,
        text = null,
        sea = "Oceanic"
    )


    private val night2 = LocalNight(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Clear clouds",
        tempmax = 22.0,
        tempmin = 8.0,
        peipsi = null,
        text = null,
        sea = "Oceanic"
    )
    private val day3 = LocalDay(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Risk of glaze",
        tempmax = 22.0,
        tempmin = 10.0,
        peipsi = null,
        text = null,
        sea = null
    )


    private val night3 = LocalNight(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Snow",
        tempmax = 22.0,
        tempmin = 8.0,
        peipsi = null,
        text = null,
        sea = null
    )

    val listOfLocalForecast = hashSetOf(
        LocalForecast(
        "2022-05-17", day1, night1
        ),
        LocalForecast(
            "2022-05-18", day2, night2
        ),
        LocalForecast(
            "2022-05-19", day3, night3
        )
    )


}