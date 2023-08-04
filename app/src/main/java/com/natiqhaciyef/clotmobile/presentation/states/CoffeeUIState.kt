package com.natiqhaciyef.clotmobile.presentation.states

import com.natiqhaciyef.clotmobile.data.models.CoffeeModel

data class CoffeeUIState(
    var list: List<CoffeeModel> = listOf(),
    var errorMessage: String? = null,
    var isLoading: Boolean = false,
    var singleCoffee: CoffeeModel? = null
)

