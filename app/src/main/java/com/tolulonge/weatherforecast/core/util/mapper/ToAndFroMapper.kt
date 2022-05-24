package com.tolulonge.weatherforecast.core.util.mapper

interface ToAndFroMapper<T, E> {

    fun from(e: E): T

    fun mTo(t: T): E
}
