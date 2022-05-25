package com.tolulonge.weatherforecast.presentation.state.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PresentationPlace(
    val name: String?,
    val phenomenon: String?,
    val tempmax: String?,
    val tempmin: String?
): Parcelable