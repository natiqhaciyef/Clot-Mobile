package com.natiqhaciyef.clotmobile.domain.usecases.clothes

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.domain.repositories.ClothesRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateClothesUseCase @Inject constructor(
    private val clothesRepo: ClothesRepository
) {

    suspend operator fun invoke(clothesModel: ClothesModel) = flow{
        val response = clothesRepo.updateClothes(clothesModel)
        emit(Resource.loading(null))

        if (response.success == 1) {
            emit(Resource.success(BaseUseCase.UPDATE_SUCCESS))
        }else{
            emit(Resource.error(BaseUseCase.UPDATE_FAIL, response.message))
        }
    }
}
