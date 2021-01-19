package com.azhar.core.data.source.local

import com.azhar.core.data.source.local.entity.FoodEntity
import com.azhar.core.data.source.local.room.FoodAppDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val foodAppDao: FoodAppDao) {

    fun getAllFood(): Flow<List<FoodEntity>> = foodAppDao.getAllFood()

    fun getFavoriteFood(): Flow<List<FoodEntity>> = foodAppDao.getFavoriteFood()

    suspend fun insertFood(foodList: List<FoodEntity>) = foodAppDao.insertFood(foodList)

    fun setFavoriteFood(food: FoodEntity, newState: Boolean) {
        food.isFavorite = newState
        foodAppDao.updateFavoriteFood(food)
    }

    fun searchFood(keySearch: String): Flow<List<FoodEntity>> = foodAppDao.searchFood(keySearch)

}