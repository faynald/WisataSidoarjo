package com.farhanrv.wisatasidoarjo.data.network

import com.google.gson.annotations.SerializedName

data class WisataListResponse(
    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("kode_wisata")
    val kodeWisata: String,

    @field:SerializedName("thumbnail")
    val thumbnail: String
)