package com.natiqhaciyef.clotmobile.domain.usecases.remote.coffee

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.data.models.CoffeeModel
import com.natiqhaciyef.clotmobile.domain.repositories.CoffeeRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateCoffeeUseCase @Inject constructor(
    private val coffeeRepo: CoffeeRepository
) {

    suspend operator fun invoke(coffee: CoffeeModel) = flow{
        val response = coffeeRepo.updateCoffee(coffee)
        emit(Resource.loading(null))

        if (response.success == 1) {
            emit(Resource.success(BaseUseCase.UPDATE_SUCCESS))
        } else {
            emit(Resource.error(BaseUseCase.UPDATE_FAIL, response.message))
        }
    }

}