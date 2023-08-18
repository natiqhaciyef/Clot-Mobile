package com.natiqhaciyef.clotmobile.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.clotmobile.common.Status
import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.domain.models.ClothesMappedModel
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
): BaseViewModel() {

    val cartUIState = mutableStateOf(CartUIState())

    fun getCartByUserId(userId: Int){
        viewModelScope.launch {
            getAllCartsUseCase.invoke(userId).collectLatest { result ->
                val defaultUIState = CartUIState()
                when(result.status){
                    Status.SUCCESS -> {
                        if (result.data != null)
                            cartUIState.value = defaultUIState.copy(list = result.data)
                        cartUIState.value = defaultUIState.copy(isLoading = false)
                    }

                    Status.ERROR -> {
                        cartUIState.value = defaultUIState.copy(errorMessage = result.message)
                        cartUIState.value = defaultUIState.copy(isLoading = false)
                    }

                    Status.LOADING -> {
                        cartUIState.value = defaultUIState.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun insertCart(
        cartModel: CartModel,
        onSuccess: () -> Unit = { },
        onError: () -> Unit = { },
        onLoading: () -> Unit = { }
    ) {
        viewModelScope.launch {
            insertCartUseCase.invoke(cartModel).collectLatest { result ->
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


    fun updateCart(
        cartModel: CartModel,
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