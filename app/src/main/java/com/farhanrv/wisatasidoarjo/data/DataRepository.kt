package com.farhanrv.wisatasidoarjo.data

import com.farhanrv.wisatasidoarjo.data.local.DBDataSource
import com.farhanrv.wisatasidoarjo.data.local.WisataDetailEntity
import com.farhanrv.wisatasidoarjo.data.local.WisataListEntity
import com.farhanrv.wisatasidoarjo.data.network.ApiResponse
import com.farhanrv.wisatasidoarjo.data.network.NetworkDataSource
import com.farhanrv.wisatasidoarjo.data.network.WisataDetailResponse
import com.farhanrv.wisatasidoarjo.data.network.WisataListResponse
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val network: NetworkDataSource,
    private val local: DBDataSource,
    private val executors: DataExecutors
) {
    fun getWisata(): Flow<DataResource<List<WisataListEntity>>> =
        object :
            com.farhanrv.wisatasidoarjo.data.DataBoundResource<List<WisataListEntity>, List<WisataListResponse>>() {
            override fun loadFromDB(): Flow<List<WisataListEntity>> {
                return local.getAllWisata()
            }

            override fun shouldFetch(data: List<WisataListEntity>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<WisataListResponse>>> =
                network.getAllWisata()

            override suspend fun saveCallResult(data: List<WisataListResponse>) {
                val list = DataListMapper.mapResponsesToEntities(data)
                local.insertWisata(list)
            }
        }.asFlow()

    fun getDetailWisata(kode: String): Flow<DataResource<List<WisataDetailEntity>>> =
        object :
            com.farhanrv.wisatasidoarjo.data.DataBoundResource<List<WisataDetailEntity>, List<WisataDetailResponse>>() {
            override fun loadFromDB(): Flow<List<WisataDetailEntity>> {
                return local.getDetail(kode)
            }

            override fun shouldFetch(data: List<WisataDetailEntity>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<WisataDetailResponse>>> =
                network.getDetail(kode)

            override suspend fun saveCallResult(data: List<WisataDetailResponse>) {
                val detail = DataDetailMapper.mapResponsesToEntities(data)
                local.insertDetail(detail)
            }
        }.asFlow()

    fun getFavorites(): Flow<List<WisataListEntity>> {
        return local.getFavorites()
    }

    fun setFavorite(kode: String, state: Boolean) {
        executors.diskIO().execute { local.setFavorite(kode, state) }
    }
}