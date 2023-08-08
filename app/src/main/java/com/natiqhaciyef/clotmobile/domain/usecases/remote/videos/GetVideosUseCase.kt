package com.natiqhaciyef.clotmobile.domain.usecases.remote.videos

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.remote.VideoRemoteRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val videoRepo: VideoRemoteRepository
) {

    suspend operator fun invoke() = flow{
        emit(Resource.loading(null))

        val response = videoRepo.getVideo()
        if (response.videos != null){
            emit(Resource.success(response.videos))
        }else{
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}