package com.natiqhaciyef.clotmobile.domain.repositories.local

import com.natiqhaciyef.clotmobile.data.db.entities.ClothesEntity

interface ClothesLocalRepository {

    suspend fun getAllClothes(): List<ClothesEntity>?

    suspend fun getClothesByDate(date: String): List<ClothesEntity>?

    suspend fun insertClothes(clothesEntity: ClothesEntity)

    suspend fun removeClothes(clothesEntity: ClothesEntity)
}