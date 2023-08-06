package com.natiqhaciyef.clotmobile.data.models

import androidx.media3.common.MediaItem
import com.google.gson.annotations.SerializedName


data class VideoModel(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String,
    @SerializedName("link")
    var link: String,
    @SerializedName("field")
    var field: String,      // Field = "Android" or "Backend" or "RoadMap"
    @SerializedName("category")
    var category: String,   // Video Categories
    @SerializedName("subtitles")
    var subtitles: String = "",
    @SerializedName("publish_date")
    var publishDate: String,
    @SerializedName("is_active")
    var isActive: Boolean,
){
    val mediaItem = MediaItem.fromUri(link)
}
