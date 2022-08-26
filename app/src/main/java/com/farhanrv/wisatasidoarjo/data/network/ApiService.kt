package com.farhanrv.wisatasidoarjo.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(".")
    suspend fun getWisata(): List<WisataListResponse>

    @GET("{kode_wisata}")
    suspend fun getDetail(@Path("kode_wisata") kodeWisata: String): List<WisataDetailResponse>
}