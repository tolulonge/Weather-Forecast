package com.tolulonge.weatherforecast.remote

import com.tolulonge.weatherforecast.data.model.*


object TestRemoteData {

    private val listOfPlaces = listOf(
        DataPlace("Harku","Cloudy Weather",3.0,4.0),
        DataPlace("Tartu","Moderate Shower",10.0,16.0),
    )

    private val listOfWinds = listOf(
        DataWind("West wind","Kuusiku",3.0,4.0),
        DataWind("Tartu","Moderate Shower",10.0,16.0),
    )
    private val day1 = DataDay(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Cloudy Weather",
        tempmax = 22.0,
        tempmin = 10.0,
        peipsi = null,
        text = null,
        sea = "Oceanic"
    )
    private val night1 = DataNight(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Light Shower",
        tempmax = 22.0,
        tempmin = 10.0,
        peipsi = null,
        text = null,
        sea = "Oceanic"
    )
    private val day2 = DataDay(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Heavy Rain",
        tempmax = 22.0,
        tempmin = 10.0,
        peipsi = null,
        text = null,
        sea = "Oceanic"
    )


    private val night2 = DataNight(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Clear clouds",
        tempmax = 22.0,
        tempmin = 8.0,
        peipsi = null,
        text = null,
        sea = "Oceanic"
    )
    private val day3 = DataDay(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Risk of glaze",
        tempmax = 22.0,
        tempmin = 10.0,
        peipsi = null,
        text = null,
        sea = null
    )


    private val night3 = DataNight(
        places = listOfPlaces,
        winds = listOfWinds,
        phenomenon = "Snow",
        tempmax = 22.0,
        tempmin = 8.0,
        peipsi = null,
        text = null,
        sea = null
    )

    val listOfDataForecast = listOf(DataForecast(
        "2022-05-24",day1,night1
    ),
        DataForecast(
            "2022-05-25",day2,night2
        ),
        DataForecast(
            "2022-05-26", day3, night3
        ),
        DataForecast(
            "2022-05-27", day3, night3
        )
    )





}
