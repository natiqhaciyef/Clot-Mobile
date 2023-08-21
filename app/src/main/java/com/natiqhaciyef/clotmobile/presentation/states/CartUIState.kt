package com.natiqhaciyef.clotmobile.presentation.states

import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.domain.models.CartMappedModel

data class CartUIState(
    var list: List<CartMappedModel> = listOf(),
    var errorMessage: String? = null,
    var isLoading : Boolean = false
)
