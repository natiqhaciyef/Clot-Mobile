package com.natiqhaciyef.clotmobile.domain.usecases.remote.videos

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.data.models.VideoModel
import com.natiqhaciyef.clotmobile.domain.repositories.VideoRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateVideoUseCase @Inject constructor(
    private val videoRepo: VideoRepository
) {

    suspend operator fun invoke(videoModel: VideoModel) = flow{
        emit(Resource.loading(null))

        val response = videoRepo.updateVideo(videoModel)
        if (response.success == 1){
            emit(Resource.success(response))
        }else{
            emit(Resource.error(BaseUseCase.UPDATE_FAIL, null))
        }
    }

}