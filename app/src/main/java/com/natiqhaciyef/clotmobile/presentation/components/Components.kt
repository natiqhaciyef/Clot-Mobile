package com.natiqhaciyef.clotmobile.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.clotmobile.R
import com.natiqhaciyef.clotmobile.common.util.classes.NavItemModel
import com.natiqhaciyef.clotmobile.ui.theme.AppDarkTeal
import com.natiqhaciyef.clotmobile.ui.theme.AppLightPurple
import com.natiqhaciyef.clotmobile.ui.theme.AppPurple
import com.natiqhaciyef.clotmobile.ui.theme.AppYellow

@Composable
fun NavBar(
    selectedIndex: MutableState<Int>,
    list: MutableList<NavItemModel> = mutableListOf(
        NavItemModel(image = R.drawable.home_icon, title = "Home"),
        NavItemModel(image = R.drawable.academy_building_icon, title = "Courses"),
        NavItemModel(image = R.drawable.user_profile_icon, title = "Profile"),
    )
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(AppPurple, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            list.forEachIndexed { index, icon ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(11f)
                        .clickable { selectedIndex.value = index },
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = if (selectedIndex.value == index)
                            Modifier.offset(y = (-8).dp)
                        else
                            Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    if (selectedIndex.value == index) AppYellow
                                    else Color.Transparent,
                                    shape = CircleShape
                                )
                                .size(45.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = icon.image),
                                contentDescription = "content",
                                modifier = Modifier.size(25.dp),
                                tint = if (selectedIndex.value == index) Color.White else AppLightPurple
                            )
                        }
                        AnimatedVisibility(visible = (selectedIndex.value == index)) {
                            Text(
                                text = icon.title,
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}