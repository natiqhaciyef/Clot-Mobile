package com.natiqhaciyef.clotmobile.domain.usecases.remote.cart

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CartRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveCartUseCase @Inject constructor(
    private val cartRemoteRepository: CartRemoteRepository
) {

    suspend operator fun invoke(id: Int) = flow {
        val response = cartRemoteRepository.deleteCart(id)
        emit(Resource.loading(null))

        if (response.success == 1){
            emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
        }else{
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, null))
        }

    }

}