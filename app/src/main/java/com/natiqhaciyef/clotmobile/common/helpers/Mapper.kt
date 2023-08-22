package com.natiqhaciyef.clotmobile.common.helpers

import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.domain.models.CartMappedModel
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

    fun mapToCartMappedModel(cartModel: CartModel): CartMappedModel{
        return CartMappedModel(
            id = cartModel.id,
            userId = cartModel.userId,
            title = cartModel.title,
            details = cartModel.details,
            image = cartModel.image,
            size = cartModel.size.toSQLiteMutableList(),
            colors = cartModel.colors.toSQLiteMutableList(),
            totalCargoPrice = cartModel.totalCargoPrice,
            totalPrice = cartModel.totalPrice,
            priceCurrency = cartModel.priceCurrency,
            type = cartModel.type,
            amount = cartModel.amount,
            date = stringToFormattedDate(cartModel.date)
        )
    }

    fun mapToCartModel(cartMappedModel: CartMappedModel): CartModel{
        return CartModel(
            id = cartMappedModel.id,
            userId = cartMappedModel.userId,
            title = cartMappedModel.title,
            details = cartMappedModel.details,
            image = cartMappedModel.image,
            size = cartMappedModel.size.toMutableList().toSQLiteString(),
            colors = cartMappedModel.colors.toMutableList().toSQLiteString(),
            totalCargoPrice = cartMappedModel.totalCargoPrice,
            totalPrice = cartMappedModel.totalPrice,
            priceCurrency = cartMappedModel.priceCurrency,
            type = cartMappedModel.type,
            amount = cartMappedModel.amount,
            date = dateToFormattedLocalTime(cartMappedModel.date)
        )
    }
}