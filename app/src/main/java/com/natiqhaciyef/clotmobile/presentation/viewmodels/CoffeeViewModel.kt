package com.natiqhaciyef.clotmobile.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.clotmobile.common.Status
import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.data.models.CoffeeModel
import com.natiqhaciyef.clotmobile.domain.usecases.coffee.GetAllCoffeeUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.coffee.GetCoffeeByIdUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.coffee.InsertCoffeeUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.coffee.RemoveCoffeeUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.coffee.UpdateCoffeeUseCase
import com.natiqhaciyef.clotmobile.presentation.states.ClothesUIState
import com.natiqhaciyef.clotmobile.presentation.states.CoffeeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(
    private val getAllCoffeeUseCase: GetAllCoffeeUseCase,
    private val getCoffeeByIdUseCase: GetCoffeeByIdUseCase,
    private val insertCoffeeUseCase: InsertCoffeeUseCase,
    private val removeCoffeeUseCase: RemoveCoffeeUseCase,
    private val updateCoffeeUseCase: UpdateCoffeeUseCase,
) : BaseViewModel() {
    val coffeeUIState = mutableStateOf(CoffeeUIState())

    init {
        getAllCoffee()
    }

    private fun getAllCoffee() {
        viewModelScope.launch {
            getAllCoffeeUseCase.invoke().collectLatest { result ->
                val defaultState = CoffeeUIState()
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            coffeeUIState.value =
                                defaultState.copy(list = result.data, isLoading = false)
                    }

                    Status.ERROR -> {
                        coffeeUIState.value =
                            defaultState.copy(errorMessage = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        coffeeUIState.value = defaultState.copy(isLoading = true)
                    }
                }
            }
        }
    }


    fun getCoffeeById(id: Int) {
        viewModelScope.launch {
            getCoffeeByIdUseCase.invoke(id = id).collectLatest { result ->
                val defaultState = CoffeeUIState()
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            coffeeUIState.value =
                                defaultState.copy(singleCoffee = result.data, isLoading = false)
                    }

                    Status.ERROR -> {
                        coffeeUIState.value =
                            defaultState.copy(errorMessage = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        coffeeUIState.value = defaultState.copy(isLoading = true)
                    }
                }
            }
        }
    }


    fun insertCoffee(
        coffeeModel: CoffeeModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            insertCoffeeUseCase.invoke(coffeeModel).collectLatest { result ->
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

    fun updateCoffee(
        coffeeModel: CoffeeModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            updateCoffeeUseCase.invoke(coffeeModel).collectLatest { result ->
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


    fun removeCoffee(
        id: Int,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            removeCoffeeUseCase.invoke(id = id).collectLatest { result ->
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