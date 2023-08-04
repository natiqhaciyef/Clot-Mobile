package com.natiqhaciyef.clotmobile.data.source.remote

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.ClothesResult
import com.natiqhaciyef.clotmobile.data.network.services.ClothesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClothesDataSource(
    private val service: ClothesService
) {
    suspend fun getAllClothes(): ClothesResult = withContext(Dispatchers.IO){
        service.getAllClothes()
    }

    suspend fun getClothesByTitle(title: String): ClothesModel = withContext(Dispatchers.IO){
        service.getClothesByTitle(title)
    }

    suspend fun insertClothes(
        clothesModel: ClothesModel
    ): CRUDResponse = withContext(Dispatchers.IO){
        service.insertClothes(
            title = clothesModel.title,
            details = clothesModel.details,
            brand = clothesModel.brand,
            price = clothesModel.price,
            priceCurrency = clothesModel.priceCurrency,
            size = clothesModel.size,
            color = clothesModel.color,
            image = clothesModel.image,
            type = clothesModel.type,
            cargoPrice = clothesModel.cargoPrice,
            season = clothesModel.season,
            country = clothesModel.country,
            isActive = clothesModel.isActive,
        )
    }

    suspend fun deleteClothes(
        id: Int,
    ): CRUDResponse = withContext(Dispatchers.IO){
        service.deleteClothes(id = id)
    }

    suspend fun updateClothes(
        clothesModel: ClothesModel
    ): CRUDResponse = withContext(Dispatchers.IO){
        service.updateClothes(
            id = clothesModel.id,
            title = clothesModel.title,
            details = clothesModel.details,
            brand = clothesModel.brand,
            price = clothesModel.price,
            priceCurrency = clothesModel.priceCurrency,
            size = clothesModel.size,
            color = clothesModel.color,
            image = clothesModel.image,
            type = clothesModel.type,
            cargoPrice = clothesModel.cargoPrice,
            season = clothesModel.season,
            country = clothesModel.country,
            isActive = clothesModel.isActive,
        )
    }

}