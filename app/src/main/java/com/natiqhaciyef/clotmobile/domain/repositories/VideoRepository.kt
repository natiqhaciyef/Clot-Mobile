package com.natiqhaciyef.clotmobile.domain.repositories

import com.natiqhaciyef.clotmobile.data.models.VideoModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.VideoResult

interface VideoRepository {

    suspend fun getVideo(): VideoResult

    suspend fun searchVideo(title: String): VideoModel?

    suspend fun insertVideo(videoModel: VideoModel): CRUDResponse

    suspend fun deleteVideo(id: Int): CRUDResponse

    suspend fun updateVideo(videoModel: VideoModel): CRUDResponse

}