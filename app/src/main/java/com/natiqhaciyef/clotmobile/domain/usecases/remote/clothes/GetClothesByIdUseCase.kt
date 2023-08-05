package com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.ClothesRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetClothesByIdUseCase @Inject constructor(
    private val clothesRepo: ClothesRepository
) {

    suspend operator fun invoke(id: Int) = flow{
        emit(Resource.loading(null))
        val clothes = clothesRepo.getClothesById(id)
        if (clothes != null){
            emit(Resource.success(clothes))
        }else{
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}