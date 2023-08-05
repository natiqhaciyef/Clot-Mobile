package com.natiqhaciyef.clotmobile.data.models

import androidx.media3.common.MediaItem


data class VideoModel(
    var title: String,
    var link: String,
    var field: String,      // Field = "Android" or "Backend" or "RoadMap"
    var category: String,   // Video Categories
    var subtitles: String = "",
    var timePeriod: String,
    var publishDate: String,
    var isActive: Boolean,
    val mediaItem: MediaItem = MediaItem.fromUri(link)
)
