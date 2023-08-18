package com.natiqhaciyef.clotmobile.domain.usecases.remote.cart

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CartRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCartsUseCase @Inject constructor(
    private val cartsRemoteRepository: CartRemoteRepository
) {

    suspend operator fun invoke(userId: Int) = flow {
        emit(Resource.loading(null))

        val response = cartsRemoteRepository.getCarts(userId)
        if (response.cartTable != null)
            emit(Resource.success(response.cartTable))
        else
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
    }

}