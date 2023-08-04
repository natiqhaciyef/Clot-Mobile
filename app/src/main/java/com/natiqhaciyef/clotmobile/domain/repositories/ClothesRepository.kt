package com.natiqhaciyef.clotmobile.domain.repositories

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.ClothesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ClothesRepository {

    suspend fun getAllClothes(): ClothesResult

    suspend fun getClothesByTitle(title: String): ClothesModel?

    suspend fun insertClothes(clothesModel: ClothesModel): CRUDResponse

    suspend fun deleteClothes(id: Int): CRUDResponse
    suspend fun updateClothes(clothesModel: ClothesModel): CRUDResponse


}