package com.natiqhaciyef.clotmobile.presentation.states

import com.natiqhaciyef.clotmobile.data.models.VideoModel

data class VideoUIState(
    var list: List<VideoModel> = listOf(),
    var errorMessage: String? = null,
    var isLoading: Boolean = false,
    var singleVideo: VideoModel? = null
)