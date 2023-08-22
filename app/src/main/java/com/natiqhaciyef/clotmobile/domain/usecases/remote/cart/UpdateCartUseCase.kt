package com.natiqhaciyef.clotmobile.domain.usecases.remote.cart

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.common.helpers.Mapper
import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.domain.models.CartMappedModel
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CartRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateCartUseCase @Inject constructor(
    private val cartRemoteRepository: CartRemoteRepository
){

    suspend operator fun invoke(cartModel: CartMappedModel) = flow{
        val response = cartRemoteRepository.updateCart(Mapper.mapToCartModel(cartModel))
        emit(Resource.loading(null))

        if (response.success == 1){
            emit(Resource.success(BaseUseCase.UPDATE_SUCCESS))
        }else{
            emit(Resource.error(BaseUseCase.UPDATE_FAIL, null))
            println(response.message)
        }
    }

}