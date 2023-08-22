package com.natiqhaciyef.clotmobile.presentation.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.natiqhaciyef.clotmobile.common.helpers.colorConvertHexCode
import com.natiqhaciyef.clotmobile.common.helpers.priceConverter
import com.natiqhaciyef.clotmobile.common.helpers.priceValueConverter
import com.natiqhaciyef.clotmobile.domain.models.CartMappedModel
import com.natiqhaciyef.clotmobile.presentation.components.CustomDropDownMenu
import com.natiqhaciyef.clotmobile.presentation.components.fonts.Opensans
import com.natiqhaciyef.clotmobile.presentation.viewmodels.CartViewModel
import com.natiqhaciyef.clotmobile.presentation.viewmodels.ClothesViewModel
import com.natiqhaciyef.clotmobile.ui.theme.AppDarkPurple
import com.natiqhaciyef.clotmobile.ui.theme.AppGray
import com.natiqhaciyef.clotmobile.ui.theme.AppPurple
import java.time.LocalDateTime

@Composable
fun ClothesDetailsScreen(
    navController: NavController,
    id: Int,
    userId: Int,
    clothesViewModel: ClothesViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    cartViewModel.getCarts()
    val clothesUIState = remember { clothesViewModel.clothesUIState }
    val selectedSize = remember { mutableStateOf("") }
    val selectedColor = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf(1) }
    val totalCargoPrice = remember { mutableStateOf(0.0) }

    val filteredList = clothesUIState.value.list.filter { it.id == id }

    if (filteredList.isNotEmpty()) {
        val singleClothes = filteredList[0]
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                painter = rememberImagePainter(singleClothes.image),
                contentDescription = "Clothes image",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = singleClothes.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppDarkPurple,
                fontFamily = Opensans.opensans
            )

            Spacer(modifier = Modifier.height(25.dp))
            CustomDropDownMenu(
                title = "Sizes",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                list = singleClothes.size,
                selectedOption = selectedSize
            )

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomDropDownMenu(
                    title = "Colors",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(55.dp)
                        .padding(horizontal = 20.dp),
                    list = singleClothes.color,
                    selectedOption = selectedColor
                )
                Spacer(modifier = Modifier.width(10.dp))

                Card(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(35.dp)
                        .fillMaxWidth(),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Black),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(colorConvertHexCode(selectedColor.value).toColorInt())
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(colorConvertHexCode(selectedColor.value).toColorInt()))
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .border(width = 1.dp, color = AppDarkPurple, shape = RoundedCornerShape(10.dp)),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .align(Alignment.CenterStart),
                        text = "Amount",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = AppGray,
                    )

                    Row(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(30.dp)
                                .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                                .background(AppPurple)
                                .clickable {
                                    if (amount.value > 1)
                                        amount.value = (amount.value - 1)
                                },
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(15.dp)
                                    .align(Alignment.Center),
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Remove",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))

                        Text(
                            modifier = Modifier
                                .width(40.dp),
                            text = amount.value.toString(),
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.width(15.dp))
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(30.dp)
                                .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                                .background(AppPurple)
                                .clickable {
                                    amount.value = (amount.value + 1)
                                },
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(15.dp)
                                    .align(Alignment.Center),
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(),
                text = "Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(),
                text = singleClothes.details,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppGray,
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(),
                text = "Shipping & Fees",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(10.dp))

            if (amount.value > 1 && amount.value * singleClothes.price <= 200) {
                totalCargoPrice.value =
                    singleClothes.cargoPrice + 0.1 * singleClothes.cargoPrice * amount.value
                Text(
                    modifier = Modifier,
                    text = "${priceValueConverter(totalCargoPrice.value)} ${
                        priceConverter(
                            singleClothes.priceCurrency
                        )
                    } for shipping price",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    color = AppGray,
                )

            } else if (amount.value > 1 || amount.value * singleClothes.price > 200) {
                totalCargoPrice.value = 0.0
                Text(
                    modifier = Modifier,
                    text = "Free shipping multiple addresses",
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    color = AppGray,
                )
            } else {
                totalCargoPrice.value = singleClothes.cargoPrice

                Text(
                    modifier = Modifier,
                    text = "${priceValueConverter(totalCargoPrice.value)} ${
                        priceConverter(
                            singleClothes.priceCurrency
                        )
                    }  for shipping price",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    color = AppGray,
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp),
                onClick = {
//                          navController.navigate()
                    val cartModel = CartMappedModel(
                        id = 0,
                        userId = userId,
                        title = singleClothes.title,
                        details = singleClothes.details,
                        size = listOf(selectedSize.value),
                        colors = listOf(selectedColor.value),
                        image = singleClothes.image,
                        totalPrice = totalCargoPrice.value + (amount.value * singleClothes.price),
                        priceCurrency = singleClothes.priceCurrency,
                        totalCargoPrice = totalCargoPrice.value,
                        type = singleClothes.type,
                        amount = amount.value,
                        date = LocalDateTime.now()
                    )

                    cartViewModel.insertCart(cartModel = cartModel)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppPurple,
                ),
                shape = RoundedCornerShape(12.dp),
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Submit: ${priceValueConverter((singleClothes.price * amount.value + totalCargoPrice.value))} ${priceConverter(singleClothes.priceCurrency)}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}