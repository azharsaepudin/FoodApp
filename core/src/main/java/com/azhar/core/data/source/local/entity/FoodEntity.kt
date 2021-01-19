package com.azhar.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_food")
data class FoodEntity(

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "foodId")
        var foodId: String,

        @ColumnInfo(name = "nameFood")
        val nameFood: String,

        @ColumnInfo(name = "description")
        val description: String,

        @ColumnInfo(name = "price")
        val price: String,

        @ColumnInfo(name = "code")
        val code: String,

        @ColumnInfo(name = "image")
        val image: String,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
)