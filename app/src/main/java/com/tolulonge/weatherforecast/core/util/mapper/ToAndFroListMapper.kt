package com.tolulonge.weatherforecast.core.util.mapper

interface ToAndFroListMapper<T, E> {

    fun from(e: List<E>): List<T>

    fun mTo(t: List<T>): List<E>
}
