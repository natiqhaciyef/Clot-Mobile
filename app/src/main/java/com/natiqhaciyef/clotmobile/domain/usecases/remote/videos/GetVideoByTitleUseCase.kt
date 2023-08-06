package com.natiqhaciyef.clotmobile.domain.usecases.remote.videos

import com.natiqhaciyef.clotmobile.common.Resource
import com.natiqhaciyef.clotmobile.domain.repositories.VideoRepository
import com.natiqhaciyef.techtive.domain.usecases.config.BaseUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVideoByTitleUseCase @Inject constructor(
    private val videoRepo: VideoRepository
) {

    suspend operator fun invoke(title: String) = flow{
        emit(Resource.loading(null))

        val response = videoRepo.searchVideo(title = title)
        if (response != null){
            emit(Resource.success(response))
        }else{
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}