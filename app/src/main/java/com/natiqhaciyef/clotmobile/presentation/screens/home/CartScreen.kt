package com.natiqhaciyef.clotmobile.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.clotmobile.common.helpers.dateToFormattedLocalTime
import com.natiqhaciyef.clotmobile.common.helpers.stringToFormattedDate
import com.natiqhaciyef.clotmobile.data.models.CartModel
import com.natiqhaciyef.clotmobile.presentation.viewmodels.CartViewModel
import java.time.LocalDateTime

@Composable
fun CartScreen(
    navController: NavController,
    userId: Int,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartUIState = remember { cartViewModel.cartUIState }
    if (cartUIState.value.list.isNotEmpty()){
        val filteredList = cartUIState.value.list.filter { it.userId == userId }


    }
}


@Composable
fun CartItemView(cartModel: CartModel) {


}