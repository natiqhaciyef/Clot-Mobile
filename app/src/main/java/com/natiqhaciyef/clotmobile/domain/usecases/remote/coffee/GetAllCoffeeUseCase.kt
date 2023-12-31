package com.natiqhaciyef.clotmobile.domain.usecases.remote.coffee

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.CoffeeRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCoffeeUseCase @Inject constructor(
    private val coffeeRepo: CoffeeRemoteRepository
){

    suspend operator fun invoke() = flow{
        emit(Resource.loading(listOf()))

        val coffee = coffeeRepo.getAllCoffee()
        if (coffee.coffeeList != null){
            emit(Resource.success(coffee.coffeeList))
        }else{
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}