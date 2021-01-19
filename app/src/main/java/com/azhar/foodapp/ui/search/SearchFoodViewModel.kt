package com.azhar.foodapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azhar.core.domain.model.Food
import com.azhar.core.domain.usecase.FoodUseCase

class SearchFoodViewModel(foodUseCase: FoodUseCase) : ViewModel() {


    val foodscase = foodUseCase

    lateinit var foodResult: LiveData<List<Food>>

    fun getResultRearch(keySearch: String): LiveData<List<Food>> {

        foodResult = foodscase.searchFood(keySearch).asLiveData()

        return foodResult
    }


}