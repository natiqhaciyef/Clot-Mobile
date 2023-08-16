package com.natiqhaciyef.clotmobile.domain.repositories.impl

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.ClothesResult
import com.natiqhaciyef.clotmobile.data.source.remote.ClothesRemoteDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.ClothesRemoteRepository

class ClothesRemoteRepositoryImpl(private val ds: ClothesRemoteDataSource): ClothesRemoteRepository {
    override suspend fun getAllClothes(): ClothesResult = ds.getAllClothes()

    override suspend fun insertClothes(clothesModel: ClothesModel): CRUDResponse = ds.insertClothes(clothesModel)

    override suspend fun deleteClothes(id: Int): CRUDResponse = ds.deleteClothes(id)

    override suspend fun updateClothes(clothesModel: ClothesModel): CRUDResponse = ds.updateClothes(clothesModel)
}