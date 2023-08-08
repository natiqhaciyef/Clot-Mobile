package com.natiqhaciyef.clotmobile.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.natiqhaciyef.clotmobile.common.Status
import com.natiqhaciyef.clotmobile.data.models.VideoModel
import com.natiqhaciyef.clotmobile.domain.usecases.remote.videos.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val getAllVideosUseCase: GetVideosUseCase,
    val player: Player,
): BaseViewModel() {
    private var _videoState = mutableStateOf<List<VideoModel>>(listOf())
    val videoState get() = _videoState

    val errorMessageVideo = mutableStateOf<String?>(null)
    val isLoading = mutableStateOf(false)

    init {
        player.prepare()
        getAllVideos()
    }

    private fun getAllVideos(){
        viewModelScope.launch {
            getAllVideosUseCase.invoke().collectLatest { result ->
                when(result.status){
                    Status.LOADING -> {
                        isLoading.value = true
                    }

                    Status.ERROR -> {
                        errorMessageVideo.value = result.message
                        isLoading.value = false
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            _videoState.value = result.data

                        isLoading.value = false
                    }
                }
            }
        }
    }

    fun filterVideoByFields(videos: List<VideoModel>, field: String, category: String) =
        videos.filter {
            it.field.lowercase() == field.lowercase() && it.category.lowercase() == category.lowercase()
        }

    fun addVideoLink(link: String) {
        player.addMediaItem(MediaItem.fromUri(link.toUri()))
    }

    fun playVideo(link: String, list: List<VideoModel>) {
        player.setMediaItem(
            list.find { it.link == link }?.mediaItem ?: return
        )
    }

    fun getDuration(playerDuration: (Long) -> Unit) {
        player.addListener(@UnstableApi object : Player.Listener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    val duration = player.duration
                    playerDuration(duration)
                }
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}