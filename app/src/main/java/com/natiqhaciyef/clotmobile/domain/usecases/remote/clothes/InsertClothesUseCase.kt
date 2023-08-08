package com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.domain.repositories.remote.ClothesRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertClothesUseCase @Inject constructor(
    private val clothesRepo: ClothesRemoteRepository
) {

    suspend operator fun invoke(clothesModel: ClothesModel) = flow {
        val response = clothesRepo.insertClothes(clothesModel)
        emit(Resource.loading(null))

        if (response.success == 1) {
            emit(Resource.success(BaseUseCase.INSERT_SUCCESS))
        }else{
            emit(Resource.error(BaseUseCase.INSERT_FAIL, response.message))
        }
    }
}