package com.natiqhaciyef.clotmobile.domain.models

import com.google.gson.annotations.SerializedName

data class ClothesMappedModel(
    var id: Int,
    var title: String,
    var details: String,
    var brand: String,
    var price: Double,
    var priceCurrency: String,
    var size: List<String>,
    var color: List<String>,
    var image: String,
    var type: String,
    var category: String,
    var cargoPrice: Double,
    var season: List<String>,
    var country: String,
    var isActive: Boolean,
)
