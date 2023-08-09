package com.natiqhaciyef.clotmobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.clotmobile.presentation.screens.registration.ForgotPasswordScreen
import com.natiqhaciyef.clotmobile.presentation.screens.registration.LoginScreen
import com.natiqhaciyef.clotmobile.presentation.screens.registration.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenId.LoginScreen.name) {

        composable(route = ScreenId.SplashScreen.name){

        }

        composable(route = ScreenId.LoginScreen.name){
            LoginScreen(
                navController = navController,
            )
        }

        composable(route = ScreenId.RegisterScreen.name){
            RegisterScreen(
                navController = navController,
            )
        }

        composable(route = ScreenId.ForgotPasswordScreen.name){
            ForgotPasswordScreen(
                navController = navController
            )
        }

    }

}