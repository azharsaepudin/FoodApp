package com.azhar.core.data.source.local.room

import androidx.room.*
import com.azhar.core.data.source.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodAppDao {

    @Query("SELECT * FROM tb_food")
    fun getAllFood(): Flow<List<FoodEntity>>

    @Query("SELECT * FROM tb_food where isFavorite = 1")
    fun getFavoriteFood(): Flow<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: List<FoodEntity>)

    @Update
    fun updateFavoriteFood(food: FoodEntity)

    @Query("SELECT * FROM tb_food where nameFood LIKE :keySearch")
    fun searchFood(keySearch: String): Flow<List<FoodEntity>>

}