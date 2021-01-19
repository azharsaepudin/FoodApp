package com.azhar.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodItemResponse(

        @field:SerializedName("foodId")
        val foodId: String,

        @field:SerializedName("name_food")
        val nameFood: String,

        @field:SerializedName("description")
        val description: String,

        @field:SerializedName("price")
        val price: String,

        @field:SerializedName("code")
        val code: String,

        @field:SerializedName("image")
        val image: String

)