package com.natiqhaciyef.clotmobile.domain.models

import java.time.LocalDateTime

data class CartMappedModel(
    var id: Int = 0,
    var userId: Int,
    var title: String,
    var details: String,
    var image: String,
    var size: List<String>,
    var colors: List<String>,
    var totalPrice: Double,
    var priceCurrency: String,
    var totalCargoPrice: Double,
    var type: String,
    var amount: Int,
    var date: LocalDateTime = LocalDateTime.now(),
)
