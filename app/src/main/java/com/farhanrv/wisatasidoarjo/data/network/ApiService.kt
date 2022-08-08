package com.farhanrv.wisatasidoarjo.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun getWisata(): List<WisataListResponse>

    @GET("detail/{kode_wisata}")
    suspend fun getDetail(@Path("kode_wisata") kodeWisata: String): List<WisataDetailResponse>
}