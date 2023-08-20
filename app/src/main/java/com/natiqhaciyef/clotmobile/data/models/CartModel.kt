package com.natiqhaciyef.clotmobile.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartModel(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("user_id")
    var userId: Int,
    @SerializedName("titles")
    var titles: String,
    @SerializedName("details")
    var details: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("size")
    var size: String,
    @SerializedName("total_price")
    var totalPrice: Double,
    @SerializedName("price_currency")
    var priceCurrency: String,
    @SerializedName("total_cargo_price")
    var totalCargoPrice: Double,
    @SerializedName("type")
    var type: String,
    @SerializedName("amount")
    var amount: Int,
    @SerializedName("date")
    var date: String,
): Parcelable

