package com.farhanrv.wisatasidoarjo.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "wisata_list_entity")
data class WisataListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nama: String,

    @ColumnInfo(name = "kode_wisata")
    val kodeWisata: String,

    val thumbnail: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
) : Parcelable

