package com.farhanrv.wisatasidoarjo.data

import com.farhanrv.wisatasidoarjo.data.local.WisataListEntity
import com.farhanrv.wisatasidoarjo.data.network.WisataListResponse

object DataListMapper {
    fun mapResponsesToEntities(input: List<WisataListResponse>): List<WisataListEntity> {
        val wisataList = ArrayList<WisataListEntity>()
        input.map {
            val wisata = WisataListEntity(
                nama = it.nama,
                kodeWisata = it.kodeWisata,
                thumbnail = it.thumbnail,
                isFavorite = false
            )
            wisataList.add(wisata)
        }
        return wisataList
    }
}