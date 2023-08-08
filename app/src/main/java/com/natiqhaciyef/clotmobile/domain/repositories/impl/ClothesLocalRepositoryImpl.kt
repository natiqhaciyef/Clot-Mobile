package com.natiqhaciyef.clotmobile.domain.repositories.impl

import com.natiqhaciyef.clotmobile.data.db.entities.ClothesEntity
import com.natiqhaciyef.clotmobile.data.source.local.ClothesLocalDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.local.ClothesLocalRepository

class ClothesLocalRepositoryImpl(private val ds: ClothesLocalDataSource): ClothesLocalRepository {
    override suspend fun getAllClothes(): List<ClothesEntity>? = ds.getAllClothes()

    override suspend fun getClothesByDate(date: String): List<ClothesEntity>? = ds.getClothesByDate(date)

    override suspend fun insertClothes(clothesEntity: ClothesEntity) = ds.insertClothes(clothesEntity)

    override suspend fun removeClothes(clothesEntity: ClothesEntity) = ds.removeClothes(clothesEntity)
}