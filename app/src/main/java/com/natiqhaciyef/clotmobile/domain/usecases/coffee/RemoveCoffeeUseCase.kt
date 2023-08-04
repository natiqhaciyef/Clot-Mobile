package com.natiqhaciyef.clotmobile.domain.usecases.coffee

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.CoffeeRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveCoffeeUseCase @Inject constructor(
    private val coffeeRepo: CoffeeRepository
) {

    suspend operator fun invoke(id: Int) = flow {
        val response = coffeeRepo.deleteCoffee(id)
        emit(Resource.loading(null))

        if (response.success == 1) {
            emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
        } else {
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, response.message))
        }
    }

}