package com.natiqhaciyef.clotmobile.domain.repositories.impl

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.ClothesResult
import com.natiqhaciyef.clotmobile.data.source.remote.ClothesDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.ClothesRepository

class ClothesRepositoryImpl(private val ds: ClothesDataSource): ClothesRepository {
    override suspend fun getAllClothes(): ClothesResult = ds.getAllClothes()

    override suspend fun getClothesByTitle(title: String): ClothesModel? = ds.getClothesByTitle(title)

    override suspend fun insertClothes(clothesModel: ClothesModel): CRUDResponse = ds.insertClothes(clothesModel)

    override suspend fun deleteClothes(id: Int): CRUDResponse = ds.deleteClothes(id)

    override suspend fun updateClothes(clothesModel: ClothesModel): CRUDResponse = ds.updateClothes(clothesModel)
}