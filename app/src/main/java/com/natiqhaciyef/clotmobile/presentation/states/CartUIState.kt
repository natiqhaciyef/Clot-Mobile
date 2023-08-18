package com.natiqhaciyef.clotmobile.presentation.states

import com.natiqhaciyef.clotmobile.data.models.CartModel

data class CartUIState(
    var list: List<CartModel> = listOf(),
    var errorMessage: String? = null,
    var isLoading : Boolean = false
)
