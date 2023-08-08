package com.natiqhaciyef.clotmobile.domain.usecases.remote.videos

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.VideoRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveVideoUseCase @Inject constructor(
    private val videoRepo: VideoRemoteRepository
) {

    suspend operator fun invoke(id: Int) = flow{
        emit(Resource.loading(null))

        val response = videoRepo.deleteVideo(id)
        if (response.success == 1){
            emit(Resource.success(response))
        }else{
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, null))
        }
    }

}