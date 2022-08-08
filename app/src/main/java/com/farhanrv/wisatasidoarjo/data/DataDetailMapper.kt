package com.farhanrv.wisatasidoarjo.data

import com.farhanrv.wisatasidoarjo.data.local.WisataDetailEntity
import com.farhanrv.wisatasidoarjo.data.network.WisataDetailResponse

object DataDetailMapper {
    fun mapResponsesToEntities(input: List<WisataDetailResponse>): List<WisataDetailEntity> {
        val detailList = ArrayList<WisataDetailEntity>()
        input.map {
            val detail = WisataDetailEntity(
                nama = it.nama,
                kodeWisata = it.kodeWisata,
                lokasi = it.lokasi,
                jamOperasional = it.jamOperasional,
                biaya = it.biaya,
                gambar = it.gambar,
                googleMap = it.googleMap,
                deskripsi = it.deskripsi,
                isFavorite = false
            )
            detailList.add(detail)
        }
        return detailList
    }
}