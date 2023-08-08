package com.natiqhaciyef.clotmobile.domain.usecases.local.clothes

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.data.db.entities.ClothesEntity
import com.natiqhaciyef.clotmobile.domain.repositories.local.ClothesLocalRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveClothesLocalUseCase @Inject constructor(
    private val clothesLocalRepository: ClothesLocalRepository
) {

    suspend operator fun invoke(clothesEntity: ClothesEntity) = flow {
        clothesLocalRepository.removeClothes(clothesEntity)
        emit(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }
}