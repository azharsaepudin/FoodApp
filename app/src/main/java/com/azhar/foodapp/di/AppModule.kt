package com.azhar.foodapp.di

import com.azhar.core.domain.usecase.FoodInteractor
import com.azhar.core.domain.usecase.FoodUseCase
import com.azhar.foodapp.ui.detail.DetailFoodViewModel
import com.azhar.foodapp.ui.home.HomeViewModel
import com.azhar.foodapp.ui.search.SearchFoodViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FoodUseCase> { FoodInteractor(get()) }
}

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { DetailFoodViewModel(get()) }
    viewModel { SearchFoodViewModel(get()) }

}