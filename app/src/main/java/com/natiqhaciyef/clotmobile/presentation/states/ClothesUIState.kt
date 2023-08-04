package com.natiqhaciyef.clotmobile.presentation.states

import com.natiqhaciyef.clotmobile.data.models.ClothesModel

data class ClothesUIState(
    var list : List<ClothesModel> = listOf(),
    var errorMessage: String? = null,
    var isLoading: Boolean = false
)
