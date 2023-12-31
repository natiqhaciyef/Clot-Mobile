package com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.common.helpers.Mapper
import com.natiqhaciyef.clotmobile.domain.models.ClothesMappedModel
import com.natiqhaciyef.clotmobile.domain.repositories.remote.ClothesRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertClothesUseCase @Inject constructor(
    private val clothesRepo: ClothesRemoteRepository
) {

    suspend operator fun invoke(clothesMappedModel: ClothesMappedModel) = flow {
        val response = clothesRepo.insertClothes(Mapper.mapToClothesModel(clothesMappedModel))
        emit(Resource.loading(null))

        if (response.success == 1) {
            println(response.message)
            emit(Resource.success(BaseUseCase.INSERT_SUCCESS))
        }else{
            println(response.message)
            emit(Resource.error(BaseUseCase.INSERT_FAIL, response.message))
        }
    }
}