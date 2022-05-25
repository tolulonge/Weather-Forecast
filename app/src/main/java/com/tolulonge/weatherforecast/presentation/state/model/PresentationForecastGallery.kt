package com.tolulonge.weatherforecast.presentation.state.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PresentationForecastGallery(
    val date: String?,
    val dPhenomenon: String?,
    val dPeipsi: String?,
    val dSea: String?,
    val dTempmax: String?,
    val dTempmin: String?,
    val dText: String?,
    val nPhenomenon: String?,
    val nPeipsi: String?,
    val nSea: String?,
    val nTempmax: String?,
    val nTempmin: String?,
    val nText: String?,
): Parcelable
