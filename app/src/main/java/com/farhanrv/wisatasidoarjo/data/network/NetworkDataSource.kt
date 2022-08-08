package com.farhanrv.wisatasidoarjo.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NetworkDataSource(private val apiService: ApiService) {

    suspend fun getAllWisata(): Flow<ApiResponse<List<WisataListResponse>>> {
        return flow {
            try {
                val response = apiService.getWisata()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetail(kode: String): Flow<ApiResponse<List<WisataDetailResponse>>> {
        return flow {
            try {
                val response = apiService.getDetail(kode)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}