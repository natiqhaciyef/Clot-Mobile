package com.natiqhaciyef.clotmobile.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("details")
    var details: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("price")
    var price: Double,
    @SerializedName("price_currency")
    var priceCurrency: String,
    @SerializedName("size")
    var size: String,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("category")
    var category: String
): Parcelable

