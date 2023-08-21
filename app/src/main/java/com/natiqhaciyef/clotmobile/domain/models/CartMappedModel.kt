package com.natiqhaciyef.clotmobile.domain.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

data class CartMappedModel(
    var id: Int = 0,
    var userId: Int,
    var titles: String,
    var details: String,
    var image: String,
    var size: List<String>,
    var totalPrice: Double,
    var priceCurrency: String,
    var totalCargoPrice: Double,
    var type: String,
    var amount: Int,
    var date: LocalDateTime = LocalDateTime.now(),
)
