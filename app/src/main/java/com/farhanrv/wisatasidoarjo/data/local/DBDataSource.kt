package com.farhanrv.wisatasidoarjo.data.local

import kotlinx.coroutines.flow.Flow

class DBDataSource(private val appDao: WisataDao) {

    fun getAllWisata(): Flow<List<WisataListEntity>> = appDao.getAllWisata()

    suspend fun insertWisata(iphoneList: List<WisataListEntity>) = appDao.insertWisata(iphoneList)

    fun getDetail(slug: String): Flow<List<WisataDetailEntity>> = appDao.getDetail(slug)

    suspend fun insertDetail(detail: List<WisataDetailEntity>) = appDao.insertDetail(detail)

    fun getFavorites(): Flow<List<WisataListEntity>> = appDao.getFavorites()

    fun setFavorite(kode: String, newState: Boolean) {
        appDao.updateFavorite(kode, newState)
    }
}