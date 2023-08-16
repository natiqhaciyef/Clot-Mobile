package com.natiqhaciyef.clotmobile.domain.repositories.remote

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.ClothesResult

interface ClothesRemoteRepository {

    suspend fun getAllClothes(): ClothesResult

    suspend fun insertClothes(clothesModel: ClothesModel): CRUDResponse

    suspend fun deleteClothes(id: Int): CRUDResponse

    suspend fun updateClothes(clothesModel: ClothesModel): CRUDResponse


}