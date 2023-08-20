package com.natiqhaciyef.clotmobile.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.clotmobile.data.models.UserModel
import com.natiqhaciyef.clotmobile.presentation.components.CategoryCard
import com.natiqhaciyef.clotmobile.presentation.components.ClothesCard
import com.natiqhaciyef.clotmobile.presentation.components.OutlinedInputBox
import com.natiqhaciyef.clotmobile.presentation.components.categories.Category
import com.natiqhaciyef.clotmobile.presentation.components.fonts.Opensans
import com.natiqhaciyef.clotmobile.presentation.navigation.ScreenId
import com.natiqhaciyef.clotmobile.presentation.states.ClothesUIState
import com.natiqhaciyef.clotmobile.presentation.viewmodels.ClothesViewModel
import com.natiqhaciyef.clotmobile.presentation.viewmodels.RegistrationViewModel
import com.natiqhaciyef.clotmobile.ui.theme.AppDarkPurple
import com.natiqhaciyef.clotmobile.ui.theme.AppExtraLightPurple
import com.natiqhaciyef.clotmobile.ui.theme.AppGray
import com.natiqhaciyef.clotmobile.ui.theme.AppLightOrange
import com.natiqhaciyef.clotmobile.ui.theme.AppOrange
import com.natiqhaciyef.clotmobile.ui.theme.AppPurple
import com.natiqhaciyef.clotmobile.ui.theme.AppYellow
import com.natiqhaciyef.clotmobile.ui.theme.LightYellow
import com.natiqhaciyef.clotmobile.ui.theme.Yellow

@Composable
fun HomeScreen(
    navController: NavController,
    userId: Int,
    clothesViewModel: ClothesViewModel = hiltViewModel(),
) {
    val clothesList = remember { clothesViewModel.clothesUIState }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(215.dp)
                    .background(AppPurple)
            )
            ClothesScreen(
                navController = navController,
                userId = userId,
                clothesList = clothesList
            )
        }
    }

}

@Composable
fun ClothesScreen(
    navController: NavController,
    userId: Int,
    clothesList: MutableState<ClothesUIState>,
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val searchQuery = remember { mutableStateOf("") }
    val currentUser = registrationViewModel.firebaseRepo.auth.currentUser
    var user: UserModel? = null
    if (currentUser != null && currentUser.email != null) {
        registrationViewModel.getUser(currentUser.email!!,
            onSuccess = { u ->
                user = u
            }, onError = {
                user = null
            })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontFamily = Opensans.opensans
                        )
                    ) {
                        append("Welcome, ")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppLightOrange,
                            fontFamily = Opensans.opensans
                        )
                    ) {
                        if (user != null) {
                            append(user?.name)
                        } else {
                            append("Guest Helperson")
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp)
                    .width(220.dp)
            )

            Icon(
                modifier = Modifier
                    .padding(end = 25.dp)
                    .align(Alignment.CenterEnd)
                    .size(30.dp),
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = searchQuery.value,
            onValueChange = {
                searchQuery.value = it
            },
            shape = RoundedCornerShape(8.dp),
            readOnly = false,
            enabled = true,
            singleLine = true,
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(25.dp),
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = Color.Black
                )
            },
            placeholder = {
                Text(
                    text = "Search favourites...",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions {
                focusManager.moveFocus(FocusDirection.Down)
            },
            textStyle = TextStyle.Default.copy(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                textColor = AppDarkPurple,
                focusedBorderColor = AppYellow,
                unfocusedBorderColor = AppDarkPurple,
                trailingIconColor = AppDarkPurple,
                placeholderColor = AppGray,
                cursorColor = AppPurple
            )
        )

        Spacer(modifier = Modifier.height(45.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(horizontal = 20.dp),
                text = "Shop by Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 20.dp),
                text = "see all",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(10.dp)
        ) {
            items(Category.clothesCategories) { category ->
                CategoryCard(category)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(horizontal = 20.dp),
                text = "Top selling",
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 20.dp),
                text = "see all",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            )
        }

        LazyVerticalGrid(
            modifier = Modifier.height(700.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(clothesList.value.list.filter {
                it.title.lowercase().contains(searchQuery.value.lowercase())
            }) { clothes ->
                ClothesCard(clothes) {
                    navController.navigate("${ScreenId.ClothesDetailsScreen.name}/${clothes.id}/${userId}")
                }
            }
        }
    }
}

