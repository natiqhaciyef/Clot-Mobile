package com.natiqhaciyef.clotmobile.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.natiqhaciyef.clotmobile.data.db.entities.ClothesEntity

@Dao
interface ClothesDao {

    @Query("SELECT * FROM clothes_table")
    suspend fun getAllClothes(): List<ClothesEntity>?

    @Query("SELECT * FROM clothes_table WHERE date = :date")
    suspend fun getClothesByDate(date: String): List<ClothesEntity>?

    @Insert
    suspend fun insertClothes(clothesEntity: ClothesEntity)

    @Delete
    suspend fun removeClothes(clothesEntity: ClothesEntity)

}