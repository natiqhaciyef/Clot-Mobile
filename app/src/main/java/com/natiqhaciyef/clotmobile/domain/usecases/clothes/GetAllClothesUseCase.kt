package com.natiqhaciyef.clotmobile.domain.usecases.clothes

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.domain.repositories.ClothesRepository
import com.natiqhaciyef.clotmobile.domain.repositories.impl.ClothesRepositoryImpl
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllClothesUseCase @Inject constructor(
    private val clothesRepo: ClothesRepository
) {
    suspend operator fun invoke() = flow {
        emit(Resource.loading(listOf()))

        val clothes = clothesRepo.getAllClothes()
        if (clothes.clothes != null){
            emit(Resource.success(clothes.clothes))
        }else{
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}