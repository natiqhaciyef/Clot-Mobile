package com.natiqhaciyef.clotmobile.domain.repositories.impl

import com.natiqhaciyef.clotmobile.data.models.VideoModel
import com.natiqhaciyef.clotmobile.data.network.CRUDResponse
import com.natiqhaciyef.clotmobile.data.network.results.VideoResult
import com.natiqhaciyef.clotmobile.data.source.remote.VideoDataSource
import com.natiqhaciyef.clotmobile.domain.repositories.VideoRepository

class VideoRepositoryImpl(private val ds: VideoDataSource): VideoRepository {
    override suspend fun getVideo(): VideoResult = ds.getVideo()

    override suspend fun searchVideo(title: String): VideoModel? = ds.searchVideo(title)

    override suspend fun insertVideo(videoModel: VideoModel): CRUDResponse = ds.insertVideo(videoModel)

    override suspend fun deleteVideo(id: Int): CRUDResponse = ds.deleteVideo(id)

    override suspend fun updateVideo(videoModel: VideoModel): CRUDResponse = ds.updateVideo(videoModel)
}