package com.natiqhaciyef.clotmobile.presentation.screens.home.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.natiqhaciyef.clotmobile.common.helpers.PaymentMethodList
import com.natiqhaciyef.clotmobile.common.util.classes.PaymentChoiceModel
import com.natiqhaciyef.clotmobile.presentation.components.InputBox
import com.natiqhaciyef.clotmobile.presentation.components.InputBoxForCardDateAndCVV
import com.natiqhaciyef.clotmobile.presentation.components.InputBoxTitleForCardNumber
import com.natiqhaciyef.clotmobile.ui.theme.AppDarkPurple
import com.natiqhaciyef.clotmobile.ui.theme.AppExtraLightPurple

@Composable
fun PaymentScreen(
    navController: NavController
) {
    val selectedPayment = remember { mutableStateOf("") }
    val visaSelected = remember { mutableStateOf(false) }
    val masterCardSelected = remember { mutableStateOf(false) }
    val paypalSelected = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightPurple)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .height(280.dp)
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(AppDarkPurple)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp),
                        text = "Select payment method",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    PaymentCardItems(
                        paymentMethod = PaymentMethodList.list[0],
                        isSelected = visaSelected
                    ) {
                        selectedPayment.value = "Visa"
                        visaSelected.value = true
                        masterCardSelected.value = false
                        paypalSelected.value = false
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    PaymentCardItems(
                        paymentMethod = PaymentMethodList.list[1],
                        isSelected = masterCardSelected
                    ) {
                        selectedPayment.value = "Master card"
                        visaSelected.value = false
                        masterCardSelected.value = true
                        paypalSelected.value = false
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    PaymentCardItems(
                        paymentMethod = PaymentMethodList.list[2],
                        isSelected = paypalSelected
                    ) {
                        selectedPayment.value = "PayPal"
                        visaSelected.value = false
                        masterCardSelected.value = false
                        paypalSelected.value = true
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }

            Spacer(modifier = Modifier.height(45.dp))

            // Animate wit payment details
            if (selectedPayment.value.isNotEmpty()) {
                PaymentDetails(selectedPayment)
            }
        }
    }
}


@Composable
fun PaymentDetails(
    paymentMethod: MutableState<String>
) {
    val name = remember { mutableStateOf("") }

    val cardNumber = remember { mutableStateOf("") }
    val dateOfCard = remember { mutableStateOf("") }
    val cvvCode = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Personal information",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(20.dp))
        InputBox(
            concept = "Name",
            input = name,
            isSingleLine = true,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(20.dp))
        InputBoxTitleForCardNumber(
            concept = "Card number",
            input = cardNumber,
            paymentMethod = paymentMethod
        )
        Spacer(modifier = Modifier.height(20.dp))
        InputBoxForCardDateAndCVV(expireDate = dateOfCard, cvvCode = cvvCode)
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Composable
private fun PaymentCardItems(
    paymentMethod: PaymentChoiceModel,
    isSelected: MutableState<Boolean>,
    content: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(AppExtraLightPurple)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = paymentMethod.image),
                contentDescription = "Payment Method",
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(20.dp))

            androidx.compose.material.Text(
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                text = paymentMethod.type.mainName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(20.dp))

            RadioButton(
                selected = isSelected.value,
                onClick = { content() },
            )
        }
    }
}
