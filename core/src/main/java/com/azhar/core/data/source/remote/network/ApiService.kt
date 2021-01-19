package com.azhar.core.data.source.remote.network

import com.azhar.core.data.source.remote.response.ListFoodResponse
import retrofit2.http.GET

interface ApiService {

    @GET("getDataFood.json")
    suspend fun getFoodList(): ListFoodResponse
}