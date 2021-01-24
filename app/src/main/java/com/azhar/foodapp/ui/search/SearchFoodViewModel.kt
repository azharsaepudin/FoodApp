package com.azhar.foodapp.ui.search

import androidx.lifecycle.*
import com.azhar.core.domain.model.Food
import com.azhar.core.domain.usecase.FoodUseCase

class SearchFoodViewModel(private val foodUseCase: FoodUseCase) : ViewModel() {

    private val query = MutableLiveData<String>()

    var food: LiveData<List<Food>> = Transformations.switchMap(query) {
        val result = foodUseCase.searchFood(it).asLiveData()
        result
    }

    fun doSearch(key: String) {
        query.postValue(key)
    }


}