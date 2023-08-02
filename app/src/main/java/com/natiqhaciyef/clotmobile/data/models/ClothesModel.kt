package com.natiqhaciyef.clotmobile.data.models

data class ClothesModel(
    var id: Int,
    var title: String,
    var details: String,
    var brand: String,
    var price: Double,
    var priceCurrency: String,
    var size: Double,
    var color: String,
    var image: String,
    var type: String,
    var cargoPrice: Double,
    var seasson: String,
    var country: String,
    var isActive: Boolean,
)
