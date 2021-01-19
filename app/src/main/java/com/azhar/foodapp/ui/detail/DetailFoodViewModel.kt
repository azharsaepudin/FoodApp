package com.azhar.foodapp.ui.detail

import androidx.lifecycle.ViewModel
import com.azhar.core.domain.model.Food
import com.azhar.core.domain.usecase.FoodUseCase

class DetailFoodViewModel(private val foodUseCase: FoodUseCase) : ViewModel() {

    fun setFavoriteFood(food: Food, newStatus: Boolean) = foodUseCase.setFavoriteFood(food, newStatus)

}