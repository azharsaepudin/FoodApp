package com.azhar.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

class ListFoodResponse(

        @field:SerializedName("success")
        val success: Boolean,

        @field:SerializedName("message")
        val message: String,

        @field:SerializedName("data")
        val data: List<FoodItemResponse>

)