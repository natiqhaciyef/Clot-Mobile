package com.natiqhaciyef.clotmobile.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.natiqhaciyef.clotmobile.R
import com.natiqhaciyef.clotmobile.common.helpers.priceConverter
import com.natiqhaciyef.clotmobile.common.util.classes.CategoryModel
import com.natiqhaciyef.clotmobile.common.util.objects.DefaultImpl
import com.natiqhaciyef.clotmobile.data.models.ClothesModel
import com.natiqhaciyef.clotmobile.ui.theme.AppDarkPurple
import com.natiqhaciyef.clotmobile.ui.theme.DarkPurple


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CategoryCard(
    categoryModel: CategoryModel = CategoryModel(
        title = "Clothes",
        image = "https://firebasestorage.googleapis.com/v0/b/clotmobile-4a332.appspot.com/o/Category%2Fclothes.jpeg?alt=media&token=2c8f7155-84e7-4cec-af2c-94c30067cced",
        description = "Men clothes",
        storeList = listOf()
    )
) {
    Card(
        modifier = Modifier
            .width(90.dp)
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape),
                painter = rememberImagePainter(categoryModel.image),
                contentDescription = "Category image",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                text = categoryModel.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AppDarkPurple,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ClothesCard(
    clothesModel: ClothesModel = DefaultImpl.clothesModel
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(250.dp)
            .padding(horizontal = 5.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {

        },
        border = BorderStroke(1.dp, brush = Brush.verticalGradient(listOf(Color.White, Color.White, AppDarkPurple)))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                painter = rememberImagePainter(clothesModel.image.toUri()),
                contentDescription = "Clothes image",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                text = clothesModel.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                color = Color.Black,
                )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                text = "${clothesModel.price} ${priceConverter(clothesModel.priceCurrency)}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}