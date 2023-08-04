package com.natiqhaciyef.clotmobile.domain.usecases.coffee

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.CoffeeRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoffeeByTitleUseCase @Inject constructor(
    private val coffeeRepo: CoffeeRepository
) {

    suspend operator fun invoke(title: String) = flow{
        emit(Resource.loading(null))

        val coffee = coffeeRepo.getCoffeeByTitle(title)
        if (coffee != null) {
            emit(Resource.success(coffee))
        }else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }
}