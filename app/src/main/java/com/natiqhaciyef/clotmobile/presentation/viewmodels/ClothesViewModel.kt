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
import com.natiqhaciyef.clotmobile.domain.usecases.remote.clothes.GetClothesByIdUseCase
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
    private val getClothesByIdUseCase: GetClothesByIdUseCase,
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
//                title = "Foxanger outdoors",
//                details = "Erkek Siyah Oversize Dawn Baskılı T-shirt",
//                brand = "Foxanger",
//                price = 60.0,
//                priceCurrency = PriceCurrencies.TL.name,
//                size = listOf(ClothesSizes.S.name,ClothesSizes.M.name),
//                color = listOf(),
//                image = "https://cdn.dsmcdn.com/ty520/product/media/images/20220902/2/168370607/557754149/1/1_org_zoom.jpg",
//                type = "T-Shirt",
//                category = ClothesCategory.Clothes.name,
//                cargoPrice = 35.0,
//                season = listOf(Seasons.Summer.name, Seasons.Spring.name),
//                country = "Turkey",
//                isActive = true
//            )
//        )
    }

    private fun getAllClothes() {
        viewModelScope.launch {
            getAllClothesUseCase.invoke().collectLatest { result ->
                val defaultState = ClothesUIState()
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            clothesUIState.value =
                                defaultState.copy(list = result.data, isLoading = false)
                    }

                    Status.ERROR -> {
                        clothesUIState.value =
                            defaultState.copy(errorMessage = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        clothesUIState.value = defaultState.copy(isLoading = true)
                    }
                }
            }
        }
    }


    fun getClothesById(id: Int) {
        viewModelScope.launch {
            getClothesByIdUseCase.invoke(id = id).collectLatest { result ->
                val defaultState = ClothesUIState()
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            clothesUIState.value =
                                defaultState.copy(singleClothes = result.data, isLoading = false)
                    }

                    Status.ERROR -> {
                        clothesUIState.value =
                            defaultState.copy(errorMessage = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        clothesUIState.value = defaultState.copy(isLoading = true)
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