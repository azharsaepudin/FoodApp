package com.azhar.core.data.source.remote

import com.azhar.core.data.source.remote.network.ApiResponse
import com.azhar.core.data.source.remote.network.ApiService
import com.azhar.core.data.source.remote.response.FoodItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllFood(): Flow<ApiResponse<List<FoodItemResponse>>> {

        return flow {
            try {
                val response = apiService.getFoodList()

                if (response.success) {

                    val mDataArray = response.data


                    if (mDataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(response.data))

                    } else {
                        emit(ApiResponse.Empty)

                    }

                } else {
                    emit(ApiResponse.Error(response.message))
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))


            }
        }.flowOn(Dispatchers.IO)
    }

}