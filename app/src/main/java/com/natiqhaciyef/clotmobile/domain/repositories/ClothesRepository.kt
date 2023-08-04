package com.natiqhaciyef.clotmobile.domain.repositories

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.ClothesResult

interface ClothesRepository {

    suspend fun getAllClothes(): ClothesResult

    suspend fun getClothesById(id: Int): ClothesModel?

    suspend fun insertClothes(clothesModel: ClothesModel): CRUDResponse

    suspend fun deleteClothes(id: Int): CRUDResponse

    suspend fun updateClothes(clothesModel: ClothesModel): CRUDResponse


}