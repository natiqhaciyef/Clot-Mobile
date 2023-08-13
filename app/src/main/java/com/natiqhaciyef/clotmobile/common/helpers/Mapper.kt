package com.natiqhaciyef.clotmobile.common.helpers

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.domain.models.ClothesMappedModel

object Mapper {
    fun mapToClothesMappedModel(clothesModel: ClothesModel): ClothesMappedModel{
        return ClothesMappedModel(
            id = clothesModel.id,
            title = clothesModel.title,
            details = clothesModel.details,
            brand = clothesModel.brand,
            price = clothesModel.price,
            priceCurrency = clothesModel.priceCurrency,
            size = clothesModel.size.toSQLiteMutableList(),
            image = clothesModel.image,
            color = clothesModel.color.toSQLiteMutableList(),
            type = clothesModel.type,
            category = clothesModel.category,
            cargoPrice = clothesModel.cargoPrice,
            season = clothesModel.season.toSQLiteMutableList(),
            country = clothesModel.country,
            isActive = clothesModel.isActive == 1
        )
    }


    fun mapToClothesModel(clothesMappedModel: ClothesMappedModel): ClothesModel{
        return ClothesModel(
            id = clothesMappedModel.id,
            title = clothesMappedModel.title,
            details = clothesMappedModel.details,
            brand = clothesMappedModel.brand,
            price = clothesMappedModel.price,
            priceCurrency = clothesMappedModel.priceCurrency,
            size = clothesMappedModel.size.toMutableList().toSQLiteString(),
            image = clothesMappedModel.image,
            color = clothesMappedModel.color.toMutableList().toSQLiteString(),
            type = clothesMappedModel.type,
            category = clothesMappedModel.category,
            cargoPrice = clothesMappedModel.cargoPrice,
            season = clothesMappedModel.season.toMutableList().toSQLiteString(),
            country = clothesMappedModel.country,
            isActive = if (clothesMappedModel.isActive) 1 else 0
        )
    }
}