package com.farhanrv.wisatasidoarjo.data.network

import com.google.gson.annotations.SerializedName

data class WisataDetailResponse(
    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("kode_wisata")
    val kodeWisata: String,

    @field:SerializedName("lokasi")
    val lokasi: String,

    @field:SerializedName("jam_operasional")
    val jamOperasional: String,

    @field:SerializedName("biaya_masuk")
    val biaya: String,

    @field:SerializedName("images")
    val gambar: List<String>,

    @field:SerializedName("google_map")
    val googleMap: String,

    @field:SerializedName("deskripsi")
    val deskripsi: String
)