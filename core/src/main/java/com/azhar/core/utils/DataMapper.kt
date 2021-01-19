package com.azhar.core.utils

import com.azhar.core.data.source.local.entity.FoodEntity
import com.azhar.core.data.source.remote.response.FoodItemResponse
import com.azhar.core.domain.model.Food

object DataMapper {

    fun mapResponseToEntities(input: List<FoodItemResponse>): List<FoodEntity> {

        val foodList = ArrayList<FoodEntity>()
        input.map {
            val food = FoodEntity(
                    foodId = it.foodId,
                    nameFood = it.nameFood,
                    description = it.description,
                    price = it.price,
                    code = it.code,
                    image = it.image,
                    isFavorite = false
            )

            foodList.add(food)
        }

        return foodList
    }

    fun mapEntitiesToDomain(input: List<FoodEntity>): List<Food> = input.map {

        Food(

                foodId = it.foodId,
                nameFood = it.nameFood,
                description = it.description,
                price = it.price,
                code = it.code,
                image = it.image,
                isFavorite = it.isFavorite
        )
    }

    fun mapDomainToEntity(input: Food) = FoodEntity(
            foodId = input.foodId,
            nameFood = input.nameFood,
            description = input.description,
            price = input.price,
            code = input.code,
            image = input.image,
            isFavorite = input.isFavorite
    )

}