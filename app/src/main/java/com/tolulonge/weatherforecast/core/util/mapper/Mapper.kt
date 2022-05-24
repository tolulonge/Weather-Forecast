package com.tolulonge.weatherforecast.core.util.mapper

interface Mapper<in I, out O> {
    fun map(input: I): O
}