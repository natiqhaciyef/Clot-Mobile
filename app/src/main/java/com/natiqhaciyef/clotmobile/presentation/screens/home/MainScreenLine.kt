package com.natiqhaciyef.clotmobile.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Token
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.clotmobile.common.util.classes.NavItemModel
import com.natiqhaciyef.clotmobile.presentation.components.NavBar
import com.natiqhaciyef.clotmobile.presentation.navigation.BottomNavigationIndex
import com.natiqhaciyef.clotmobile.presentation.screens.home.cart.CartScreen
import com.natiqhaciyef.clotmobile.presentation.screens.home.main.HomeScreen
import com.natiqhaciyef.clotmobile.presentation.viewmodels.RegistrationViewModel

@Composable
fun MainScreenLine(
    navController: NavController,
    userId: Int,
    selectedIndex: MutableState<Int> = BottomNavigationIndex.bottomNavigationIndex,
    registrationViewModel: RegistrationViewModel = hiltViewModel(),
) {
    val isAdmin = remember { mutableStateOf(false) }
    registrationViewModel.getUserType(
        onSuccess = { user ->
            isAdmin.value = user.type == "Admin"
        },
        onError = {
            isAdmin.value = false
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavBar(
                selectedIndex = selectedIndex,
                list = if (isAdmin.value) {
                    mutableListOf(
                        NavItemModel(image = Icons.Outlined.Home, title = "Home"),
                        NavItemModel(image = Icons.Outlined.FavoriteBorder, title = "Liked"),
                        NavItemModel(image = Icons.Outlined.ShoppingCart, title = "Cart"),
                        NavItemModel(image = Icons.Outlined.AccountCircle, title = "Profile"),
                        NavItemModel(image = Icons.Outlined.Token, title = "Admin"),
                    )

                } else {
                    mutableListOf(
                        NavItemModel(image = Icons.Outlined.Home, title = "Home"),
                        NavItemModel(image = Icons.Outlined.FavoriteBorder, title = "Liked"),
                        NavItemModel(image = Icons.Outlined.ShoppingCart, title = "Cart"),
                        NavItemModel(image = Icons.Outlined.AccountCircle, title = "Profile"),
                    )
                }
            )
        }
    ) {
        it.calculateBottomPadding()
        when (selectedIndex.value) {
            0 -> {
                HomeScreen(navController = navController, userId = userId)
            }

            1 -> {
                // Saved posts screen
            }

            2 -> {
                CartScreen(navController = navController, userId = userId)
            }

            3 -> {
                // User profile
            }

            4 -> {
                // Admin profile
            }
        }
    }
}