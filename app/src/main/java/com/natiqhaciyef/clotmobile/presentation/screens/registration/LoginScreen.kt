package com.natiqhaciyef.clotmobile.presentation.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.clotmobile.R
import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.data.models.enums.UserTypes
import com.natiqhaciyef.clotmobile.presentation.components.InputBox
import com.natiqhaciyef.clotmobile.presentation.components.PasswordBox
import com.natiqhaciyef.clotmobile.presentation.components.fonts.Opensans
import com.natiqhaciyef.clotmobile.presentation.navigation.ScreenId
import com.natiqhaciyef.clotmobile.presentation.viewmodels.RegistrationViewModel
import com.natiqhaciyef.clotmobile.ui.theme.AppDarkBrown
import com.natiqhaciyef.clotmobile.ui.theme.AppRed
import com.natiqhaciyef.clotmobile.ui.theme.AppPurple
import com.natiqhaciyef.clotmobile.ui.theme.LightGray

@Composable
fun LoginScreen(
    navController: NavController,
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            text = "Sign in",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Opensans.opensans,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        InputBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            concept = "Email",
            input = email,
            isSingleLine = true,
            trailingIcon = remember { mutableStateOf(Icons.Outlined.Email) },
            type = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(15.dp))
        PasswordBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            concept = "Password",
            input = password,
            passVisibility = passwordVisibility,
            isSingleLine = true,
            onClick = {
                passwordVisibility.value = !passwordVisibility.value
            }
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .clickable {
                    navController.navigate(ScreenId.ForgotPasswordScreen.name)
                },
            text = "Forgot password ?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = AppDarkBrown,
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(50.dp),
            onClick = {
                val user = UserModel(
                    id = 0,
                    email = email.value,
                    phone = "",
                    password = password.value,
                    name = ""
                )
                registrationViewModel.loginUserFromFirebase(
                    userModel = user,
                    onSuccess = {
                        println(it.name)
                        println(it.id)
                    },
                    onError = {

                    }
                )
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppPurple
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Sign in",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                ) {
                    append("Don't have an account ?  ")
                }

                withStyle(
                    SpanStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppRed
                    )
                ) {
                    append("Sign up")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clickable {
                    navController.navigate(ScreenId.RegisterScreen.name)
                },
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(55.dp),
            onClick = {

            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(23.dp)
                        .align(Alignment.CenterStart),
                    painter = painterResource(id = R.drawable.google_svg),
                    contentDescription = "Google icon"
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 10.dp),
                    text = "Continue with Google",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(55.dp),
            onClick = {

            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier
                        .padding(start = 5.dp, bottom = 3.dp)
                        .size(25.dp)
                        .align(Alignment.CenterStart),
                    painter = painterResource(id = R.drawable.apple_svg),
                    contentDescription = "Apple icon"
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 10.dp),
                    text = "Continue with Apple",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(55.dp),
            onClick = {

            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(23.dp)
                        .align(Alignment.CenterStart),
                    painter = painterResource(id = R.drawable.facebook_svg),
                    contentDescription = "Facebook icon"
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 10.dp),
                    text = "Continue with Facebook",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}