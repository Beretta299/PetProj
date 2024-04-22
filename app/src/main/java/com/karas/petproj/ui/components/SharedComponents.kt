package com.karas.petproj.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karas.petproj.R
import com.karas.petproj.db.data.SwipeAbleUser
import kotlinx.coroutines.delay
import kotlin.math.roundToInt


@Composable
fun UserInputField(placeHolderID: Int,
                   leadingIcon: ImageVector = Icons.Rounded.AccountCircle,
                   isPassword: Boolean = false,
                   imeAction: ImeAction = ImeAction.Next, onTextSelected: (String) -> Unit = {}, err: Boolean = true) {
    val textValue = remember {
        mutableStateOf("")
    }
    val isShowPassword = remember {
        mutableStateOf(!isPassword)
    }
    OutlinedTextField(
        value = textValue.value,
        shape = RoundedCornerShape(12.dp),
        label = {
            Text(stringResource(id = placeHolderID))
        },
        leadingIcon = {
            Image(leadingIcon, contentDescription = "")
        },
        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Green,
            focusedLabelColor = Color.Blue,
            cursorColor = Color.Cyan, focusedContainerColor = Color.White, unfocusedContainerColor = colorResource(
                id = R.color.input_field_color
            )),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = if(isPassword) KeyboardType.Password else KeyboardType.Text),
        onValueChange =  {
            textValue.value = it
            onTextSelected(it)
        },
        isError = err,
        trailingIcon = {
            if(isPassword) {
                val id = if(isShowPassword.value) {
                    R.drawable.hide_password
                } else {
                    R.drawable.show_password
                }
                IconButton(onClick = { isShowPassword.value = !isShowPassword.value}) {
                    Icon(painterResource(id = id), contentDescription = "")
                }
            }
        },
        visualTransformation = if(isShowPassword.value) VisualTransformation.None else PasswordVisualTransformation())
}

@Composable
fun TitleLabel(resourceID: Int) {
    Text(text = stringResource(id = resourceID),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp)

}

@Composable
fun ActionButton(textID: Int, onClick: () -> Unit,
    isActive: Boolean = true,
                 startColor: Int = R.color.button_start_color,
                 endColor: Int = R.color.button_end_color) {
    Button(onClick = { onClick() },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier.padding(8.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = if (isActive) getActiveBrush() else getInActiveBrush()
            ), contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = textID),
                textAlign = TextAlign.Center,
                fontSize = 18.sp)
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview(showSystemUi = true)
fun LoginInputFieldPreview() {
//    UserInputField(R.string.login_label)
    var isVisible by remember { mutableStateOf(false) }

    // Transition specification for both sliding vertically and horizontally
    val enterTransition = EnterTransition.None

    val exitTransition = slideOut(
        // Custom target offset combining X and Y axes
        targetOffset = { fullSize ->
            IntOffset(
                x = -fullSize.width * 3,  // Exit to the right of the screen
                y = -fullSize.height * 3  // Exit to the bottom of the screen
            )
        },
        animationSpec = tween(durationMillis = 1000)
    )

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = isVisible,
            enter = enterTransition,
            exit = exitTransition
        ) {
            Column(modifier = Modifier.fillMaxWidth(1f).height(100.dp).background(Color.Red)) {

            }
        }

        Button(onClick = { isVisible = !isVisible }) {
            Text(if (isVisible) "Hide" else "Show")
        }
    }

    Column(modifier = Modifier.fillMaxSize(1f).background(Color.Red)) {
        Box(modifier = Modifier.background(Color.Red).width(100.dp).height(100.dp)){

        }
    }

}

@Composable
fun getActiveBrush(): Brush {
    return Brush.horizontalGradient(
        listOf(
            colorResource(id = R.color.button_start_color),
            colorResource(
                id = R.color.button_end_color
            )
        )
    )
}

@Composable
fun getInActiveBrush(): Brush {
    return Brush.horizontalGradient(
        listOf(
            colorResource(id = R.color.inactive_button_start_color),
            colorResource(
                id = R.color.inactive_button_end_color
            )
        )
    )
}

@Composable
fun SwipeCard(onSwipeLeft: () -> Unit = {},
              onSwipeRight: () -> Unit = {},
              swipeThreshold: Float = 350f,
              sensitivityFactor: Float = 2f,
              content: @Composable () -> Unit,
              nextContent: @Composable () -> Unit,
              model: SwipeAbleUser) {

    var offset by remember { mutableStateOf(0f) }
    var dismissRight by remember { mutableStateOf(false) }
    var dismissLeft by remember { mutableStateOf(false) }
    var dismissAnimState by remember { mutableStateOf(model.swipedState) }
    var dismissAnimStateCancel by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density

    println("DismissAnimState is ${dismissAnimState}")

    LaunchedEffect(dismissRight) {
        if (dismissRight) {
            dismissAnimState = 1
            delay(300)
            onSwipeRight.invoke()
            dismissAnimState = 0
            dismissRight = false

            offset = 0f
        }
    }

    LaunchedEffect(dismissLeft) {
        if (dismissLeft) {
            dismissAnimState = - 1
            delay(100)
            onSwipeLeft.invoke()
            dismissLeft = false
            dismissAnimState = 0
            offset = 0f
//            dismissAnimStateCancel = true
        }
    }

    nextContent()

    Box(modifier = Modifier
        .offset { IntOffset(offset.roundToInt(), 0) }
        .pointerInput(Unit) {
            detectHorizontalDragGestures(onDragEnd = {
                println("Log for testing offset ${offset} treshhold ${swipeThreshold}")

                if(offset > swipeThreshold) {
                    dismissRight = true
                    offset = 0f
                } else if(offset < -swipeThreshold) {
                    dismissLeft = true
                    dismissAnimStateCancel = false
                } else {
                    offset = 0f
                }

            }) { change, dragAmount ->

                offset += (dragAmount / density) * sensitivityFactor

                when {
                    offset > swipeThreshold -> {

                    }

                    offset < -swipeThreshold -> {

                    }
                }
                if (change.positionChange() != Offset.Zero) change.consume()
            }
        }
        .graphicsLayer(
            alpha = 10f - animateFloatAsState(if (dismissRight) 1f else 0f, label = "").value,
            rotationZ = animateFloatAsState(offset / 50, label = "").value,
            translationX = animateFloatAsState(offset / 50, label = "").value
        )) {
//        if(dismissAnimState == 0) {
            content()
//        }

        val exitLeftAnim = slideOut(
            // Custom target offset combining X and Y axes
            targetOffset = { fullSize ->
                IntOffset(
                    x = -fullSize.width * 3,  // Exit to the right of the screen
                    y = -fullSize.height * 3  // Exit to the bottom of the screen
                )
            },
            animationSpec = tween(durationMillis = 300)
        ) + fadeOut()

        val exitRightAnim = slideOut(
            // Custom target offset combining X and Y axes
            targetOffset = { fullSize ->
                IntOffset(
                    x = fullSize.width * 3,  // Exit to the right of the screen
                    y = -fullSize.height * 3  // Exit to the bottom of the screen
                )
            },
            animationSpec = tween(durationMillis = 1000)
        ) + fadeOut()

        AnimatedVisibility(visible = dismissAnimState == - 1,
            enter = EnterTransition.None,
            exit = if(dismissAnimStateCancel) ExitTransition.None else exitLeftAnim
        ) {
            println("We entered zero state ${dismissAnimState}")
            content()
        }

//        AnimatedVisibility(visible = dismissAnimState == 1,
//            enter = EnterTransition.None,
//            exit = exitRightAnim
//        ) {
//            println("We entered zero state ${dismissAnimState}")
//            content()
//        }
    }
}

fun getEnterAnimation(currentValue: Int): EnterTransition {
    return when (currentValue) {
//        -1 -> slideInVertically {
//            20000
//        } + expandVertically(
//            // Expand from the top.
//            expandFrom = Alignment.Bottom
//        ) + fadeIn(
//            // Fade in with the initial alpha of 0.3f.
//            initialAlpha = 0.3f
//        )
//        1 -> slideInHorizontally { it } + fadeIn()
        else -> EnterTransition.None
    }
}

fun getExitAnimation(currentValue: Int): ExitTransition {
    return when (currentValue) {
        -1 -> slideOut(
            // Custom target offset combining X and Y axes
            targetOffset = { fullSize ->
                IntOffset(
                    x = -fullSize.width * 3,  // Exit to the right of the screen
                    y = -fullSize.height * 3  // Exit to the bottom of the screen
                )
            },
            animationSpec = tween(durationMillis = 1000)
        )
        1 -> slideOut(
            // Custom target offset combining X and Y axes
            targetOffset = { fullSize ->
                IntOffset(
                    x = fullSize.width * 3,  // Exit to the right of the screen
                    y = -fullSize.height * 3  // Exit to the bottom of the screen
                )
            },
            animationSpec = tween(durationMillis = 1000)
        )
        else -> slideOut(
            // Custom target offset combining X and Y axes
            targetOffset = { fullSize ->
                IntOffset(
                    x = -fullSize.width * 3,  // Exit to the right of the screen
                    y = -fullSize.height * 3  // Exit to the bottom of the screen
                )
            },
            animationSpec = tween(durationMillis = 1000)
        )
    }
}