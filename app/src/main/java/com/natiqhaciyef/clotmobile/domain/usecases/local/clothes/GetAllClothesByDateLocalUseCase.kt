package com.natiqhaciyef.clotmobile.domain.usecases.local.clothes

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.local.ClothesLocalRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllClothesByDateLocalUseCase @Inject constructor(
    private val clothesLocalRepository: ClothesLocalRepository
) {

    suspend operator fun invoke(date: String) = flow{
        emit(Resource.loading(null))

        val response = clothesLocalRepository.getClothesByDate(date)

        if (response != null){
            emit(Resource.success(response))
        }else{
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}