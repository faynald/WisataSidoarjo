package com.farhanrv.wisatasidoarjo.data

import com.farhanrv.wisatasidoarjo.data.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class DataBoundResource<ResultType, RequestType> {

    private var result: Flow<DataResource<ResultType>> = flow {
        emit(DataResource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(DataResource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        DataResource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        DataResource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(DataResource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { DataResource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDB(): Flow<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
    protected abstract suspend fun saveCallResult(data: RequestType)
    fun asFlow(): Flow<DataResource<ResultType>> = result
}