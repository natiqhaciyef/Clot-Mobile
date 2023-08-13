package com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.common.helpers.Mapper
import com.natiqhaciyef.clotmobile.domain.models.ClothesMappedModel
import com.natiqhaciyef.clotmobile.domain.repositories.remote.ClothesRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllClothesUseCase @Inject constructor(
    private val clothesRepo: ClothesRemoteRepository
) {
    suspend operator fun invoke() = flow {
        emit(Resource.loading(listOf()))

        val clothes = clothesRepo.getAllClothes()
        val mappedClothes = mutableListOf<ClothesMappedModel>()


        if (clothes.clothes != null){
            clothes.clothes.forEach { element ->
                mappedClothes.add(Mapper.mapToClothesMappedModel(element))
            }
            emit(Resource.success(mappedClothes.toList()))
        }else{
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}