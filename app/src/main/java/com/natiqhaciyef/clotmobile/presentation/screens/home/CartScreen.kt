package com.natiqhaciyef.clotmobile.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowRight
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.natiqhaciyef.clotmobile.common.helpers.priceConverter
import com.natiqhaciyef.clotmobile.common.helpers.priceValueConverter
import com.natiqhaciyef.clotmobile.domain.models.CartMappedModel
import com.natiqhaciyef.clotmobile.presentation.viewmodels.CartViewModel
import com.natiqhaciyef.clotmobile.ui.theme.AppExtraLightPurple
import com.natiqhaciyef.clotmobile.ui.theme.AppPurple
import com.natiqhaciyef.clotmobile.ui.theme.AppYellow
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartScreen(
    navController: NavController,
    userId: Int,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    cartViewModel.getCarts()
    val cartUIState = remember { cartViewModel.cartUIState }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { (screenWidth - 80).dp.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)

    if (cartUIState.value.list.isNotEmpty()) {
        val filteredList = cartUIState.value.list.filter { it.userId == userId }
        var totalPrice = 0.0

        filteredList.forEach { totalPrice += it.totalPrice }
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = "Cart information",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.75f)
                ) {
                    items(filteredList) { cart ->
                        if (cartUIState.value.list.isNotEmpty()) {
                            val dismissState = rememberDismissState(
                                confirmStateChange = {
                                    if (it == DismissValue.DismissedToStart)
                                        cartViewModel.removeCart(cart.id)
                                    true
                                }
                            )

                            SwipeToDismiss(
                                state = dismissState,
                                background = { },
                                directions = setOf(DismissDirection.EndToStart)
                            ) {
                                CartItemView(cartModel = cart)
                            }
                        } else {
                            CartItemView(cartModel = cart)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 70.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 10.dp)
                    .clip(
                        shape = CircleShape
                    )
                    .background(
                        shape = CircleShape,
                        color = AppPurple
                    )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Submit: ${priceValueConverter(totalPrice)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .swipeable(
                            state = swipeableState,
                            anchors = anchors,
                            thresholds = { _, _ -> FractionalThreshold(0.95f) },
                            orientation = Orientation.Horizontal
                        )
                        .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) },
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(60.dp)
                            .padding(5.dp)
                            .align(Alignment.CenterStart)
                            .background(Color.White, shape = CircleShape)
                            .clip(shape = CircleShape)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.Center),
                            imageVector = Icons.Outlined.KeyboardDoubleArrowRight,
                            contentDescription = "Icon",
                            tint = AppYellow
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun CartItemView(cartModel: CartMappedModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(AppExtraLightPurple),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp),
                painter = rememberImagePainter(cartModel.image),
                contentDescription = "Cart image",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = cartModel.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        ) {
                            append("Cargo price: ")
                        }

                        withStyle(
                            SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        ) {
                            append(
                                "${priceValueConverter(cartModel.totalCargoPrice)} ${
                                    priceConverter(
                                        cartModel.priceCurrency
                                    )
                                }"
                            )
                        }
                    },
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        ) {
                            append("Amount: ")
                        }

                        withStyle(
                            SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        ) {
                            append("${cartModel.amount}")
                        }
                    },
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        ) {
                            append("Price: ")
                        }

                        withStyle(
                            SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        ) {
                            append(
                                "${priceValueConverter(cartModel.totalPrice)} ${
                                    priceConverter(
                                        cartModel.priceCurrency
                                    )
                                }"
                            )
                        }
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))

            }
        }
    }
}