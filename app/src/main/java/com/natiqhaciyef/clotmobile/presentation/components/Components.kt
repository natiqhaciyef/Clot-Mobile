package com.natiqhaciyef.clotmobile.presentation.components

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.ui.PlayerView
import coil.compose.rememberAsyncImagePainter
import com.natiqhaciyef.clotmobile.R
import com.natiqhaciyef.clotmobile.common.util.classes.NavItemModel
import com.natiqhaciyef.clotmobile.presentation.components.fonts.Lobster
import com.natiqhaciyef.clotmobile.presentation.viewmodels.VideoPlayerViewModel
import com.natiqhaciyef.clotmobile.ui.theme.AppDarkBrown
import com.natiqhaciyef.clotmobile.ui.theme.AppGray
import com.natiqhaciyef.clotmobile.ui.theme.AppBrown
import com.natiqhaciyef.clotmobile.ui.theme.AppExtraLightBrown
import com.natiqhaciyef.clotmobile.ui.theme.AppYellow
import kotlin.math.ceil
import kotlin.math.floor

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
            .background(AppBrown, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
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
                                tint = if (selectedIndex.value == index) Color.White else AppExtraLightBrown
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

@Composable
fun InputBoxTitle(
    modifier: Modifier = Modifier,
    modifierInput: Modifier = Modifier,
    concept: String,
    input: MutableState<String>,
    isSingleLine: Boolean,
    type: KeyboardType = KeyboardType.Text,
    prefix: String = "",
    icon: MutableState<ImageVector?> = mutableStateOf(null),
    onClick: (String) -> Unit = { }
) {
    val focusManager = LocalFocusManager.current

   Text(
        modifier = modifier
            .padding(start = 20.dp)
            .fillMaxWidth(),
        text = concept,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
    )
    Spacer(modifier = Modifier.height(10.dp))
    OutlinedTextField(
        modifier = modifierInput
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        value = input.value,
        onValueChange = {
            input.value = it
        },
        shape = RoundedCornerShape(8.dp),
        enabled = true,
        singleLine = isSingleLine,
        readOnly = false,
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = type,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            if (icon.value != null) {
                androidx.compose.material3.Icon(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(35.dp)
                        .clickable {
                            onClick(input.value)
                        },
                    imageVector = icon.value!!,
                    contentDescription = "Icon"
                )
            }
        },
        prefix = {
            if (prefix.isNotEmpty()) {
                Text(
                    text = prefix,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            } else {
                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = AppDarkBrown,
            focusedBorderColor = AppExtraLightBrown,
            cursorColor = AppDarkBrown,
            focusedTextColor = AppDarkBrown,
            unfocusedTextColor = AppDarkBrown,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = AppGray,
            unfocusedPlaceholderColor = AppGray
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
        ),
        placeholder = {
            androidx.compose.material3.Text(
                text = "Enter ${concept.lowercase()}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    )
}


@Composable
fun OutlinedInputBox(
    modifier: Modifier = Modifier,
    concept: String,
    input: MutableState<String>,
    isSingleLine: Boolean,
    type: KeyboardType = KeyboardType.Text,
    prefix: String = "",
    trailingIcon: MutableState<ImageVector?> = mutableStateOf(null),
    leadingIcon: MutableState<ImageVector?> = mutableStateOf(null),
    onClick: (String) -> Unit = { }
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        value = input.value,
        onValueChange = {
            input.value = it
        },
        shape = RoundedCornerShape(8.dp),
        enabled = true,
        singleLine = isSingleLine,
        readOnly = false,
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = type,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            if (trailingIcon.value != null) {
                androidx.compose.material3.Icon(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(28.dp)
                        .clickable {
                            onClick(input.value)
                        },
                    imageVector = trailingIcon.value!!,
                    contentDescription = "Icon"
                )
            }
        },
        prefix = {
            if (prefix.isNotEmpty()) {
                Text(
                    text = prefix,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            } else {
                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = AppDarkBrown,
            focusedBorderColor = AppExtraLightBrown,
            cursorColor = AppDarkBrown,
            focusedTextColor = AppDarkBrown,
            unfocusedTextColor = AppDarkBrown,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = AppGray,
            unfocusedPlaceholderColor = AppGray
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
        ),
        placeholder = {
            androidx.compose.material3.Text(
                text = "Enter ${concept.lowercase()}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    )
}


@Composable
fun OutlinedPasswordBox(
    modifier: Modifier = Modifier,
    concept: String,
    input: MutableState<String>,
    passVisibility: MutableState<Boolean>,
    isSingleLine: Boolean,
    type: KeyboardType = KeyboardType.Text,
    prefix: String = "",
    trailingIcon: MutableState<ImageVector?> = mutableStateOf(null),
    leadingIcon: MutableState<ImageVector?> = mutableStateOf(null),
    onClick: (String) -> Unit = { },
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        value = input.value,
        onValueChange = {
            input.value = it
        },
        shape = RoundedCornerShape(8.dp),
        enabled = true,
        singleLine = isSingleLine,
        readOnly = false,
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = type,
            imeAction = ImeAction.Next
        ),
        visualTransformation = if (passVisibility.value) VisualTransformation.None
        else PasswordVisualTransformation(),
        trailingIcon = {
            if (trailingIcon.value != null) {
                androidx.compose.material3.Icon(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(28.dp)
                        .clickable {
                            onClick(input.value)
                        },
                    imageVector = trailingIcon.value!!,
                    contentDescription = "Icon"
                )
            }
        },
        prefix = {
            if (prefix.isNotEmpty()) {
                Text(
                    text = prefix,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            } else {
                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = AppDarkBrown,
            focusedBorderColor = AppExtraLightBrown,
            cursorColor = AppDarkBrown,
            focusedTextColor = AppDarkBrown,
            unfocusedTextColor = AppDarkBrown,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = AppGray,
            unfocusedPlaceholderColor = AppGray
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
        ),
        placeholder = {
            Text(
                text = "Enter ${concept.lowercase()}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    )
}


@Composable
fun InputBox(
    modifier: Modifier = Modifier,
    concept: String,
    input: MutableState<String>,
    isSingleLine: Boolean,
    type: KeyboardType = KeyboardType.Text,
    prefix: String = "",
    trailingIcon: MutableState<ImageVector?> = mutableStateOf(null),
    leadingIcon: MutableState<ImageVector?> = mutableStateOf(null),
    onClick: (String) -> Unit = { }
) {
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        value = input.value,
        onValueChange = {
            input.value = it
        },
        shape = RoundedCornerShape(8.dp),
        enabled = true,
        singleLine = isSingleLine,
        readOnly = false,
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = type,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            if (trailingIcon.value != null) {
                androidx.compose.material3.Icon(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(28.dp)
                        .clickable {
                            onClick(input.value)
                        },
                    imageVector = trailingIcon.value!!,
                    contentDescription = "Icon"
                )
            }
        },
        prefix = {
            if (prefix.isNotEmpty()) {
                Text(
                    text = prefix,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            } else {
                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        },
        colors = TextFieldDefaults.colors(
            cursorColor = AppDarkBrown,
            focusedTextColor = AppDarkBrown,
            unfocusedTextColor = AppDarkBrown,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = AppGray,
            unfocusedPlaceholderColor = AppGray,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
        ),
        placeholder = {
            Text(
                text = "Enter ${concept.lowercase()}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    )
}


@Composable
fun PasswordBox(
    modifier: Modifier = Modifier,
    concept: String,
    input: MutableState<String>,
    passVisibility: MutableState<Boolean>,
    isSingleLine: Boolean,
    type: KeyboardType = KeyboardType.Text,
    prefix: String = "",
    onClick: (String) -> Unit = { },
) {
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        value = input.value,
        onValueChange = {
            input.value = it
        },
        shape = RoundedCornerShape(8.dp),
        enabled = true,
        singleLine = isSingleLine,
        readOnly = false,
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = type,
            imeAction = ImeAction.Next
        ),
        visualTransformation = if (passVisibility.value) VisualTransformation.None
        else PasswordVisualTransformation(),
        trailingIcon = {
            if (passVisibility.value)
                androidx.compose.material3.Icon(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(28.dp)
                        .clickable {
                            onClick(input.value)
                        },
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "Icon"
                )
            else
                androidx.compose.material3.Icon(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(28.dp)
                        .clickable {
                            onClick(input.value)
                        },
                    imageVector = Icons.Default.VisibilityOff,
                    contentDescription = "Icon"
                )
        },
        prefix = {
            if (prefix.isNotEmpty()) {
                Text(
                    text = prefix,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            } else {
                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        },
        colors = TextFieldDefaults.colors(
            cursorColor = AppDarkBrown,
            focusedTextColor = AppDarkBrown,
            unfocusedTextColor = AppDarkBrown,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = AppGray,
            unfocusedPlaceholderColor = AppGray,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
        ),
        placeholder = {
            Text(
                text = "Enter ${concept.lowercase()}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    )
}


@Composable
fun CustomDropDownTitleSelectionBox(
    selectedOption: MutableState<String>,
    title: String,
    nonSelectedOption: String = "Options",
    list: List<String>,
    isEnabled: Boolean = true,
    fontSize: Int = 20
) {
    Text(
        modifier = Modifier
            .padding(start = 20.dp)
            .fillMaxWidth(),
        text = title,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
    )
    Spacer(modifier = Modifier.height(10.dp))
    CustomDropDownMenu(
        title = nonSelectedOption,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        list = list,
        selectedOption = selectedOption,
        isEnabled = true
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDownMenu(
    title: String,
    modifier: Modifier,
    list: List<String>,
    selectedOption: MutableState<String>,
    isEnabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier
            .border(
                1.dp,
                AppDarkBrown,
                shape = RoundedCornerShape(10.dp)
            ),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        androidx.compose.material.TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            value = selectedOption.value,
            onValueChange = { },
            textStyle = TextStyle.Default.copy(
                color = AppDarkBrown,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            readOnly = true,
            label = {
                Text(
                    text = title,
                    color = AppGray,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = AppDarkBrown
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },

            ) {
            list.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption.value = option
                        expanded = false
                    },
                    enabled = isEnabled
                ) {
                    Text(
                        text = option,
                        color = AppDarkBrown,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}


@Composable
fun ImageSelection(
    image: MutableState<Uri?>,
    context: Context,
    permissionLauncher: ActivityResultLauncher<String>,
    activityResultLauncher: ActivityResultLauncher<Intent>,
    isPermissionGranted: MutableState<Boolean>,
) {
    Image(
        painter = if (image.value != null) rememberAsyncImagePainter(image.value)
        else painterResource(id = R.drawable.non),
        contentDescription = "Image",
        modifier = Modifier
            .size(200.dp)
            .clickable {
                imageSelector(
                    context = context,
                    permissionLauncher = permissionLauncher,
                    activityResultLauncher = activityResultLauncher,
                    isPermissionGranted = isPermissionGranted
                )
            },
        contentScale = if (image.value != null) ContentScale.Crop
        else ContentScale.Fit
    )
}

@Composable
fun VideoPlayerItem(
    link: String,
    videoViewModel: VideoPlayerViewModel = hiltViewModel(),
    videoModifier: Modifier = Modifier
        .fillMaxWidth()
        .height(250.dp),
    extraAction: @Composable () -> Unit = { }
) {
    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(45.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontSize = 25.sp,
                        fontFamily = Lobster.lobster,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Discover your skills and build your career for the future with, ")
                }
                withStyle(
                    SpanStyle(
                        fontSize = 25.sp,
                        fontFamily = Lobster.lobster,
                        color = AppBrown,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Techtive")
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        AndroidView(
            factory = { context ->

                PlayerView(context).also {
                    videoViewModel.addVideoLink(link)
                    it.player = videoViewModel.player
                }
            },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                    }

                    else -> Unit
                }
            },
            modifier = videoModifier
//                .aspectRatio(16 / 9f)
        )

        extraAction()
    }
}

@Composable
fun VideoSelectorAndPlayer(
    link: MutableState<String>,
    timePeriod: MutableState<String>,
    videoPlayerViewModel: VideoPlayerViewModel = hiltViewModel(),
) {
    val selectVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { result ->
            result?.let { uri ->
                link.value = uri.toString()
                videoPlayerViewModel.addVideoLink(uri.toString())
                videoPlayerViewModel.getDuration { duration ->
                    timePeriod.value = "$duration"
                }
            }
        }
    )

    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AndroidView(
            factory = { context ->
                PlayerView(context).also {
//                    it.player = courseDetailsViewModel.player
                }
            },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                    }

                    else -> Unit
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)

        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            modifier = Modifier
                .width(230.dp)
                .height(55.dp),
            onClick = {
                selectVideoLauncher.launch("video/mp4")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppYellow
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Select Video",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


fun imageSelector(
    context: Context,
    permissionLauncher: ActivityResultLauncher<String>,
    activityResultLauncher: ActivityResultLauncher<Intent>,
    isPermissionGranted: MutableState<Boolean>
) {

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        isPermissionGranted.value = true
    } else {
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    if (isPermissionGranted.value) {
        val intentToGallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activityResultLauncher.launch(intentToGallery)
    }
}