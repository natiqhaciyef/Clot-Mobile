package com.natiqhaciyef.clotmobile.data.source.remote

import com.natiqhaciyef.clotmobile.data.models.VideoModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.VideoResult
import com.natiqhaciyef.clotmobile.data.network.services.VideoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRemoteDataSource (private val service: VideoService) {

    suspend fun getVideo(): VideoResult = withContext(Dispatchers.IO){
        service.getVideo()
    }

    suspend fun searchVideo(title: String): VideoModel? = withContext(Dispatchers.IO){
        service.searchVideo(title)
    }

    suspend fun insertVideo(videoModel: VideoModel): CRUDResponse = withContext(Dispatchers.IO){
        service.insertVideo(videoModel)
    }

    suspend fun deleteVideo(id: Int): CRUDResponse = withContext(Dispatchers.IO){
        service.deleteVideo(id)
    }

    suspend fun updateVideo(videoModel: VideoModel): CRUDResponse = withContext(Dispatchers.IO){
        service.updateVideo(videoModel)
    }

}