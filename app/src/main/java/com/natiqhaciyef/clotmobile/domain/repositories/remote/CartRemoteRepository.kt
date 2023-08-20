package com.natiqhaciyef.clotmobile.domain.repositories.remote

import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.CartResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CartRemoteRepository {

    suspend fun getCarts(): CartResult

    suspend fun insertCart(cartModel: CartModel): CRUDResponse

    suspend fun updateCart(cartModel: CartModel): CRUDResponse

    suspend fun deleteCart(id: Int): CRUDResponse
}