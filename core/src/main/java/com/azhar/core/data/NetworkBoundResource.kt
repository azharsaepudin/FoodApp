package com.azhar.core.data

import com.azhar.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*
import java.lang.Exception


abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {

        emit(Resource.Loading())
        val sourceData = loadFromDb().first()

        if (shouldFetchData(sourceData)) {
            emit(Resource.Loading())

            try {

                when (val apiResponse = createCall().first()) {
                    is ApiResponse.Success -> {
                        saveCallResult(apiResponse.data)
                        emitAll(loadFromDb().map {
                            Resource.Success(it)
                        })

                    }

                    is ApiResponse.Empty -> {
                        emitAll(loadFromDb().map {
                            Resource.Success(it)
                        })
                    }

                    is ApiResponse.Error -> {
                        onFetchFailed()

                        emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                    }
                }

            } catch (e: Exception) {
                emit(Resource.Error<ResultType>(e.message.toString()))
            }
        } else {

            emitAll(loadFromDb().map {
                Resource.Success(it)
            })
        }
    }


    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDb(): Flow<ResultType>

    protected abstract fun shouldFetchData(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result

}