package com.natiqhaciyef.clotmobile.presentation.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.natiqhaciyef.clotmobile.R
import com.natiqhaciyef.clotmobile.presentation.components.InputBox
import com.natiqhaciyef.clotmobile.presentation.components.fonts.Opensans
import com.natiqhaciyef.clotmobile.presentation.viewmodels.RegistrationViewModel
import com.natiqhaciyef.clotmobile.ui.theme.AppBrown
import com.natiqhaciyef.clotmobile.ui.theme.AppExtraLightBrown
import com.natiqhaciyef.clotmobile.ui.theme.AppYellow
import com.natiqhaciyef.clotmobile.ui.theme.Yellow

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
    val email = remember { mutableStateOf("") }
    val composition by
    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.reset_password_coffe_anim))


    Column {
        Spacer(modifier = Modifier.height(30.dp))
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(
                containerColor = AppBrown
            ),
            shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    text = "Reset password",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Opensans.opensans,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                InputBox(
                    concept = "Email",
                    input = email,
                    isSingleLine = true,
                    type = KeyboardType.Email,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    leadingIcon = remember { mutableStateOf(Icons.Outlined.Email) }
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    text = stringResource(R.string.reset_info),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 17.sp
                )

                Spacer(modifier = Modifier.height(45.dp))

                Button(
                    modifier = Modifier
                        .width(250.dp)
                        .height(50.dp)
                        .padding(horizontal = 30.dp),
                    onClick = {

                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppYellow
                    )
                ) {
                    Text(
                        text = "Send",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}