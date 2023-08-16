package com.natiqhaciyef.clotmobile.presentation.states

import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.domain.models.ClothesMappedModel

data class ClothesUIState(
    var list : List<ClothesMappedModel> = listOf(),
    var errorMessage: String? = null,
    var isLoading: Boolean = false,
)
