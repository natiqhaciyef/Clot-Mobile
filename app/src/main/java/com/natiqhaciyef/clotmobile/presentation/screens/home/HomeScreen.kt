package com.natiqhaciyef.clotmobile.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.clotmobile.presentation.components.CategoryCard
import com.natiqhaciyef.clotmobile.presentation.components.ClothesCard
import com.natiqhaciyef.clotmobile.presentation.components.categories.Category
import com.natiqhaciyef.clotmobile.presentation.viewmodels.ClothesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
) {
    ClothesScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesScreen(
    clothesViewModel: ClothesViewModel = hiltViewModel()
) {
    val clothesList = remember { clothesViewModel.clothesUIState }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
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
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
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
        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(clothesList.value.list){ clothes ->
                ClothesCard(clothes)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

