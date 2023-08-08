package com.natiqhaciyef.clotmobile.domain.usecases.remote.coffee

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CoffeeRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoffeeByIdUseCase @Inject constructor(
    private val coffeeRepo: CoffeeRemoteRepository
) {

    suspend operator fun invoke(id: Int) = flow{
        emit(Resource.loading(null))

        val coffee = coffeeRepo.getCoffeeById(id)
        if (coffee != null) {
            emit(Resource.success(coffee))
        }else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }
}