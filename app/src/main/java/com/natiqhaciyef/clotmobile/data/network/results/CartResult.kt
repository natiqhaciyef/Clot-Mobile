package com.natiqhaciyef.clotmobile.data.network.results

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.clotmobile.data.models.CartModel

data class CartResult(
    @SerializedName("cart_table")
    var cartTable: List<CartModel>?
)
