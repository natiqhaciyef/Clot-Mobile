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
import com.natiqhaciyef.clotmobile.presentation.screens.registration.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenId.SplashScreen.name) {

        composable(route = ScreenId.SplashScreen.name) {
            SplashScreen(navController = navController)
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

        composable(
            route = "${ScreenId.MainScreenLine.name}/{userId}",
            arguments = listOf(
                navArgument("userId"){
                    type = NavType.IntType
                }
            )
        ) {
            val userId = it.arguments?.getInt("userId") ?: 0
            MainScreenLine(
                navController = navController,
                userId = userId
            )
        }

        composable(
            route = "${ScreenId.ClothesDetailsScreen.name}/{clothesId}/{userId}",
            arguments = listOf(
                navArgument("clothesId") {
                    type = NavType.IntType
                },
                navArgument("userId") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("clothesId") ?: 0
            val userId = it.arguments?.getInt("userId") ?: 0
            ClothesDetailsScreen(navController = navController, id = id, userId = userId)
        }
    }
}


object BottomNavigationIndex {
    val bottomNavigationIndex = mutableStateOf(0)
}
