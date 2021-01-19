package com.azhar.core.domain.usecase

import com.azhar.core.data.Resource
import com.azhar.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodUseCase {

    fun getAllFood(): Flow<Resource<List<Food>>>
    fun getFavoriteFood(): Flow<List<Food>>
    fun setFavoriteFood(food: Food, state: Boolean)
    fun searchFood(keySearch: String): Flow<List<Food>>

}