package com.natiqhaciyef.clotmobile.domain.usecases.remote.coffee

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.data.models.CoffeeModel
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CoffeeRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertCoffeeUseCase @Inject constructor(
    private val coffeeRepo: CoffeeRemoteRepository
) {

    suspend operator fun invoke(coffee: CoffeeModel) = flow{
        val response = coffeeRepo.insertCoffee(coffee)
        emit(Resource.loading(null))

        if (response.success == 1) {
            emit(Resource.success(BaseUseCase.INSERT_SUCCESS))
        }else{
            emit(Resource.error(BaseUseCase.INSERT_FAIL, response.message))
        }
    }

}