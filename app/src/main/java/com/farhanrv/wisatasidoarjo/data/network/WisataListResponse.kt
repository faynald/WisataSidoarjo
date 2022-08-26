package com.farhanrv.wisatasidoarjo.data.network

import com.google.gson.annotations.SerializedName

data class WisataListResponse(
    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("thumbnail")
    val thumbnail: String
)