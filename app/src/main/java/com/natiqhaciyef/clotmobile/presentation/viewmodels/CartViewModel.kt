package com.natiqhaciyef.clotmobile.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.clotmobile.common.Status
import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.domain.models.CartMappedModel
import com.natiqhaciyef.clotmobile.domain.usecases.remote.cart.GetAllCartsUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.cart.InsertCartUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.cart.RemoveCartUseCase
import com.natiqhaciyef.clotmobile.domain.usecases.remote.cart.UpdateCartUseCase
import com.natiqhaciyef.clotmobile.presentation.states.CartUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllCartsUseCase: GetAllCartsUseCase,
    private val insertCartUseCase: InsertCartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase
) : BaseViewModel() {
    val cartUIState = mutableStateOf(CartUIState())

    init {
        getCarts()
    }

    fun getCarts() {
        viewModelScope.launch {
            getAllCartsUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            cartUIState.value = cartUIState.value.copy(list = result.data)
                        cartUIState.value = cartUIState.value.copy(isLoading = false)
                    }

                    Status.ERROR -> {
                        cartUIState.value = cartUIState.value.copy(errorMessage = result.message)
                        cartUIState.value = cartUIState.value.copy(isLoading = false)
                    }

                    Status.LOADING -> {
                        cartUIState.value = cartUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun insertCart(
        cartModel: CartMappedModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            insertCartUseCase.invoke(cartModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        onSuccess()
                        println("Data sent")
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


    fun updateCart(
        cartModel: CartMappedModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            updateCartUseCase.invoke(cartModel).collectLatest { result ->
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


    fun removeCart(
        id: Int,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            removeCartUseCase.invoke(id).collectLatest { result ->
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