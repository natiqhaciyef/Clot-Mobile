package com.natiqhaciyef.clotmobile.domain.usecases.remote.cart

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CartRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCartsUseCase @Inject constructor(
    private val cartsRemoteRepository: CartRemoteRepository
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        val response = cartsRemoteRepository.getCarts()
        if (response.cartTable != null) {
            emit(Resource.success(response.cartTable))
//            println("success")
        }else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
//            println("error")
        }
    }

}