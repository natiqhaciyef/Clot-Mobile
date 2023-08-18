package com.natiqhaciyef.clotmobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.natiqhaciyef.clotmobile.presentation.screens.home.ClothesDetailsScreen
import com.natiqhaciyef.clotmobile.presentation.screens.home.MainScreenLine
import com.natiqhaciyef.clotmobile.presentation.screens.registration.ForgotPasswordScreen
import com.natiqhaciyef.clotmobile.presentation.screens.registration.LoginScreen
import com.natiqhaciyef.clotmobile.presentation.screens.registration.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenId.LoginScreen.name) {

        composable(route = ScreenId.SplashScreen.name) {

        }

        composable(route = ScreenId.LoginScreen.name) {
            LoginScreen(
                navController = navController,
            )
        }

        composable(route = ScreenId.RegisterScreen.name) {
            RegisterScreen(
                navController = navController,
            )
        }

        composable(route = ScreenId.ForgotPasswordScreen.name) {
            ForgotPasswordScreen(
                navController = navController
            )
        }

        composable(route = ScreenId.MainScreenLine.name) {
            MainScreenLine(
                navController = navController
            )
        }

        composable(
            route = "${ScreenId.ClothesDetailsScreen.name}/{clothesId}",
            arguments = listOf(
                navArgument("clothesId"){
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("clothesId") ?: 0
            ClothesDetailsScreen(navController = navController, id = id)
        }
    }
}


object BottomNavigationIndex {
    val bottomNavigationIndex = mutableStateOf(0)
}
