package com.natiqhaciyef.clotmobile.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.clotmobile.common.Status
import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.domain.usecases.clothes.GetAllClothesUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.clothes.GetClothesByIdUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.clothes.InsertClothesUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.clothes.RemoveClothesUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.clothes.UpdateClothesUseCase
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
        clothesModel: ClothesModel,
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
        clothesModel: ClothesModel,
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