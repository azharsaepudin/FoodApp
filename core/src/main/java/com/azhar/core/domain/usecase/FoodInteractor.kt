package com.azhar.core.domain.usecase

import com.azhar.core.data.Resource
import com.azhar.core.domain.model.Food
import com.azhar.core.domain.repository.IFoodAppRepository
import kotlinx.coroutines.flow.Flow

class FoodInteractor(private val foodAppRepository: IFoodAppRepository) : FoodUseCase {

    override fun getAllFood(): Flow<Resource<List<Food>>> = foodAppRepository.getAllFood()

    override fun getFavoriteFood() = foodAppRepository.getFavoriteFood()

    override fun setFavoriteFood(food: Food, state: Boolean) = foodAppRepository.setFavoriteFood(food, state)

    override fun searchFood(keySearch: String): Flow<List<Food>> = foodAppRepository.searchFood(keySearch)
}