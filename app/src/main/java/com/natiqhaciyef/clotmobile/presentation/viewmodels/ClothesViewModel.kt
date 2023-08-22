package com.natiqhaciyef.clotmobile.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.clotmobile.common.Status
import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.models.enums.ClothesCategory
import com.natiqhaciyef.clotmobile.data.models.enums.ClothesSizes
import com.natiqhaciyef.clotmobile.data.models.enums.PriceCurrencies
import com.natiqhaciyef.clotmobile.data.models.enums.Seasons
import com.natiqhaciyef.clotmobile.domain.models.ClothesMappedModel
import com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes.GetAllClothesUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes.InsertClothesUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes.RemoveClothesUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes.UpdateClothesUseCase
import com.natiqhaciyef.clotmobile.presentation.states.ClothesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val getAllClothesUseCase: GetAllClothesUseCase,
    private val insertClothesUseCase: InsertClothesUseCase,
    private val removeClothesUseCase: RemoveClothesUseCase,
    private val updateClothesUseCase: UpdateClothesUseCase
) : BaseViewModel() {
    val clothesUIState = mutableStateOf(ClothesUIState())

    init {
        getAllClothes()
//        insertClothes(
//            ClothesMappedModel(
//                id = 0,
//                title = "Casual shoes",
//                details = "Pull & Bear Erkek Casual spor ayakkabı",
//                brand = "Pull & Bear",
//                price = 660.0,
//                priceCurrency = PriceCurrencies.TL.name,
//                size = listOf("39", "40","41","42","43","44","45"),
//                color = listOf("Black", "White", "Brown"),
//                image = "https://cdn.dsmcdn.com/ty709/product/media/images/20230201/20/271461277/846742043/1/1_org_zoom.jpg",
//                type = "Shoes",
//                category = ClothesCategory.Shoes.name,
//                cargoPrice = 75.0,
//                season = listOf(Seasons.Spring.name, Seasons.Autumn.name),
//                country = "Turkey",
//                isActive = true
//            )
//        )
    }

    fun getAllClothes() {
        viewModelScope.launch {
            getAllClothesUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            clothesUIState.value =
                                clothesUIState.value.copy(list = result.data, isLoading = false)
                    }

                    Status.ERROR -> {
                        clothesUIState.value =
                            clothesUIState.value.copy(errorMessage = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        clothesUIState.value = clothesUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }


    fun insertClothes(
        clothesModel: ClothesMappedModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            insertClothesUseCase.invoke(clothesModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }


    fun updateClothes(
        clothesModel: ClothesMappedModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            updateClothesUseCase.invoke(clothesModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }


    fun removeClothes(
        id: Int,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            removeClothesUseCase.invoke(id = id).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                    }

                    Status.ERROR -> {
                        onError()
                    }

                    Status.LOADING -> {
                        onLoading()
                    }
                }
            }
        }
    }
}