package com.natiqhaciyef.clotmobile.presentation.screens.registration

import android.window.SplashScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.clotmobile.presentation.navigation.ScreenId
import com.natiqhaciyef.clotmobile.presentation.viewmodels.RegistrationViewModel


@Composable
fun SplashScreen(
    navController: NavController,
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
    registrationViewModel.firebaseRepo.auth.currentUser?.let { currentUser ->
        currentUser.email?.let { email ->
            registrationViewModel.getUser(
                email = email,
                onSuccess = {
                    navController.navigate(ScreenId.MainScreenLine.name)
                },
                onError = {
                    navController.navigate(ScreenId.LoginScreen.name)
                }
            )
        }
    }
    Column {

    }

}