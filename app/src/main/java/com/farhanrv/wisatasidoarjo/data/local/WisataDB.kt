package com.farhanrv.wisatasidoarjo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [WisataListEntity::class, WisataDetailEntity::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WisataDB : RoomDatabase() {
    abstract fun wisataDao(): WisataDao
}