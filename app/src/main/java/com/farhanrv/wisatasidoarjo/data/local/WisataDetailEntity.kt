package com.farhanrv.wisatasidoarjo.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wisata_detail_entity")
data class WisataDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nama: String,

    @ColumnInfo(name = "wisata_id")
    val wisataId: String,

    val lokasi: String,

    val gambar: List<String>,

    @ColumnInfo(name = "google_map")
    val googleMap: String,

    val deskripsi: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)
