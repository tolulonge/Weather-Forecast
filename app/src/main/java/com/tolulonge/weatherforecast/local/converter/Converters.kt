package com.tolulonge.weatherforecast.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.tolulonge.weatherforecast.core.util.parser.JsonParser
import com.tolulonge.weatherforecast.local.entities.LocalDay
import com.tolulonge.weatherforecast.local.entities.LocalNight

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
){
    @TypeConverter
    fun LocalDayJsontoString(localDay: LocalDay?) : String? {
        return jsonParser.toJson(
            localDay,
            object : TypeToken<LocalDay>(){}.type
        )
    }

    @TypeConverter
    fun StringToLocalDayJson(json: String): LocalDay?{
        return jsonParser.fromJson<LocalDay>(
            json,
            object: TypeToken<LocalDay>(){}.type
        )
    }

    @TypeConverter
    fun LocalNightJsontoString(localNight: LocalNight?) : String? {
        return jsonParser.toJson(
            localNight,
            object : TypeToken<LocalNight>(){}.type
        )
    }

    @TypeConverter
    fun StringToLocalNightJson(json: String): LocalNight?{
        return jsonParser.fromJson<LocalNight>(
            json,
            object: TypeToken<LocalNight>(){}.type
        )
    }
}