package com.natiqhaciyef.clotmobile.domain.repositories.impl

import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.CartResult
import com.natiqhaciyef.clotmobile.data.source.remote.CartRemoteDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CartRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRemoteRepositoryImpl(
    private val ds: CartRemoteDataSource
): CartRemoteRepository {

    override suspend fun getCarts(userId: Int): CartResult = ds.getCarts(userId)


    override suspend fun insertCart(cartModel: CartModel): CRUDResponse = ds.insertCart(cartModel = cartModel)


    override suspend fun updateCart(cartModel: CartModel): CRUDResponse = ds.updateCart(cartModel)

    override suspend fun deleteCart(id: Int): CRUDResponse = ds.deleteCart(id)

}