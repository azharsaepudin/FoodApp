package com.azhar.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azhar.core.domain.usecase.FoodUseCase

class FavoriteViewModel(foodUseCase: FoodUseCase) : ViewModel() {

    val getFavorite = foodUseCase.getFavoriteFood().asLiveData()
}