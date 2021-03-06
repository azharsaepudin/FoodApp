package com.azhar.foodapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azhar.core.domain.usecase.FoodUseCase

class HomeViewModel(foodUseCase: FoodUseCase) : ViewModel() {

    val food = foodUseCase.getAllFood().asLiveData()

}