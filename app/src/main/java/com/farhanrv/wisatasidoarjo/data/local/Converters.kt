package com.farhanrv.wisatasidoarjo.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>) =
        Json.encodeToString(value)

    @TypeConverter
    fun toStringList(value: String) =
        Json.decodeFromString<List<String>>(value)
}