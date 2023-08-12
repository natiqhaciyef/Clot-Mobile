package com.natiqhaciyef.clotmobile.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClothesModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("details")
    var details: String,
    @SerializedName("brand")
    var brand: String,
    @SerializedName("price")
    var price: Double,
    @SerializedName("price_currency")
    var priceCurrency: String,
    @SerializedName("size")
    var size: String,
    @SerializedName("color")
    var color: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("category")
    var category: String,
    @SerializedName("cargo_price")
    var cargoPrice: Double,
    @SerializedName("season")
    var season: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("is_active")
    var isActive: Int,
):Parcelable
