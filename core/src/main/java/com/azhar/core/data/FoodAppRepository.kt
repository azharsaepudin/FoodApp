package com.azhar.core.data

import com.azhar.core.data.source.local.LocalDataSource
import com.azhar.core.data.source.remote.RemoteDataSource
import com.azhar.core.data.source.remote.network.ApiResponse
import com.azhar.core.data.source.remote.response.FoodItemResponse
import com.azhar.core.domain.model.Food
import com.azhar.core.domain.repository.IFoodAppRepository
import com.azhar.core.utils.AppExecutors
import com.azhar.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FoodAppRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors) : IFoodAppRepository {


    override fun getAllFood(): Flow<Resource<List<Food>>> =
            object : NetworkBoundResource<List<Food>, List<FoodItemResponse>>() {

                override fun loadFromDb(): Flow<List<Food>> {

                    return localDataSource.getAllFood().map {
                        DataMapper.mapEntitiesToDomain(it)
                    }
                }

                override fun shouldFetchData(data: List<Food>?): Boolean = true

                override suspend fun createCall(): Flow<ApiResponse<List<FoodItemResponse>>> =
                        remoteDataSource.getAllFood()

                override suspend fun saveCallResult(data: List<FoodItemResponse>) {

                    val foodList = DataMapper.mapResponseToEntities(data)
                    localDataSource.insertFood(foodList)

                }

            }.asFlow()

    override fun getFavoriteFood(): Flow<List<Food>> {
        return localDataSource.getFavoriteFood().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }


    override fun setFavoriteFood(food: Food, state: Boolean) {
        val foodEntity = DataMapper.mapDomainToEntity(food)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFood(foodEntity, state) }
    }

    override fun searchFood(keySearch: String): Flow<List<Food>> {
        return localDataSource.searchFood(keySearch).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }


}