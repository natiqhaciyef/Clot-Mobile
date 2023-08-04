package com.natiqhaciyef.clotmobile.data.network.results

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.clotmobile.data.models.ClothesModel

data class ClothesResult(
    @SerializedName("clothes_table")
    val clothes: List<ClothesModel>
)
