package com.natiqhaciyef.clotmobile.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.clotmobile.common.Status
import com.natiqhaciyef.clotmobile.data.models.VideoModel
import com.natiqhaciyef.clotmobile.domain.usecases.remote.videos.GetVideoByTitleUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.videos.GetVideosUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.videos.InsertVideoUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.videos.RemoveVideoUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.videos.UpdateVideoUseCase
import com.natiqhaciyef.clotmobile.presentation.states.VideoUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase,
    private val getVideoByTitleUseCase: GetVideoByTitleUseCase,
    private val insertVideoUseCase: InsertVideoUseCase,
    private val updateVideoUseCase: UpdateVideoUseCase,
    private val removeVideoUseCase: RemoveVideoUseCase
): BaseViewModel() {
    val videoUIState = mutableStateOf(VideoUIState())

    init {
        getAllVideo()
    }

    private fun getAllVideo() {
        viewModelScope.launch {
            getVideosUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            videoUIState.value =
                                videoUIState.value.copy(list = result.data, isLoading = false)
                    }

                    Status.ERROR -> {
                        videoUIState.value =
                            videoUIState.value.copy(errorMessage = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        videoUIState.value = videoUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }


    fun getVideoById(title: String) {
        viewModelScope.launch {
            getVideoByTitleUseCase.invoke(title = title).collectLatest { result ->
                val defaultState = VideoUIState()
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            videoUIState.value =
                                defaultState.copy(singleVideo = result.data, isLoading = false)
                    }

                    Status.ERROR -> {
                        videoUIState.value =
                            defaultState.copy(errorMessage = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        videoUIState.value = defaultState.copy(isLoading = true)
                    }
                }
            }
        }
    }


    fun insertVideo(
        videoModel: VideoModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            insertVideoUseCase.invoke(videoModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }

    fun updateVideo(
        videoModel: VideoModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            updateVideoUseCase.invoke(videoModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }


    fun removeVideo(
        id: Int,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            removeVideoUseCase.invoke(id = id).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }

}