package com.karas.petproj.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.karas.petproj.R
import com.karas.petproj.main.MainViewModel
import com.karas.petproj.navigation.NavigationItem

@Composable
    fun LoginScreen(viewModel: MainViewModel, navController: NavController) {
        Surface(color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TitleLabel(resourceID = R.string.login_label)
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp))
                    UserInputField(R.string.login_label)
                    UserInputField(R.string.pass_label, leadingIcon = Icons.Rounded.Lock, true, ImeAction.Done)
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp))
                    Button(onClick = { /*TODO*/ },
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        modifier = Modifier.padding(18.dp)
                    ) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(48.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        colorResource(id = R.color.button_start_color),
                                        colorResource(
                                            id = R.color.button_end_color
                                        )
                                    )
                                )
                            ), contentAlignment = Alignment.Center
                        ) {
                            Text(text = stringResource(id = R.string.login_button_action),
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp)
                        }
                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp))
                    RegistrationLink(navController = navController)
                }
            }
        }
    }

@Composable
fun RegistrationLink(navController: NavController) {
    val registrationWordsArray = stringArrayResource(id = R.array.registration_link_text)
    val registrationActionWord = stringResource(id = R.string.registration_link_action)
    val annotatedString = buildAnnotatedString {
        registrationWordsArray.map {
            if(it == registrationActionWord) {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    pushStringAnnotation(tag = registrationActionWord, annotation = registrationActionWord)
                    append(it)
                }
            } else {
                append("$it ")
            }
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it,it).firstOrNull()?.let {
            navController.navigate(NavigationItem.Registration.route)
        }
    })
}