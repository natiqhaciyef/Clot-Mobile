package com.natiqhaciyef.clotmobile.data.source.remote

import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.CartResult
import com.natiqhaciyef.clotmobile.data.network.services.CartService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRemoteDataSource(
    private val service: CartService
) {

    suspend fun getCarts(): CartResult = withContext(Dispatchers.IO){
        service.getCarts()
    }

    suspend fun insertCart(cartModel: CartModel): CRUDResponse = withContext(Dispatchers.IO){
        service.insertCart(
            id = cartModel.id,
            userId = cartModel.userId,
            title = cartModel.titles,
            details = cartModel.details,
            image = cartModel.image,
            size = cartModel.size,
            totalPrice = cartModel.totalPrice,
            totalCargoPrice = cartModel.totalCargoPrice,
            priceCurrency = cartModel.priceCurrency,
            type = cartModel.type,
            amount = cartModel.amount,
            date = cartModel.date
        )
    }

    suspend fun updateCart(cartModel: CartModel): CRUDResponse = withContext(Dispatchers.IO){
        service.updateCart(
            id = cartModel.id,
            userId = cartModel.userId,
            title = cartModel.titles,
            details = cartModel.details,
            image = cartModel.image,
            size = cartModel.size,
            totalPrice = cartModel.totalPrice,
            totalCargoPrice = cartModel.totalCargoPrice,
            priceCurrency = cartModel.priceCurrency,
            type = cartModel.type,
            amount = cartModel.amount,
            date = cartModel.date
        )
    }

    suspend fun deleteCart(id: Int): CRUDResponse = withContext(Dispatchers.IO){
        service.deleteCart(id)
    }
}