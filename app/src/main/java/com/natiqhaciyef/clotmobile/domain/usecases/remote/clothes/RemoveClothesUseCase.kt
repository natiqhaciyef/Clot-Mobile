package com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.ClothesRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveClothesUseCase @Inject constructor(
    private val clothesRepo: ClothesRemoteRepository
) {

    suspend operator fun invoke(id: Int) = flow{
        val response = clothesRepo.deleteClothes(id)
        emit(Resource.loading(null))

        if (response.success == 1) {
            emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
        }else{
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, response.message))
        }
    }
}