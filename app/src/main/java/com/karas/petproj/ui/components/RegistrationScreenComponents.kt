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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Person
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.karas.petproj.R
import com.karas.petproj.main.MainViewModel


@Composable
fun RegistrationScreen(viewModel: MainViewModel, navController: NavController) {
    Surface(color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TitleLabel(resourceID = R.string.registration_label)
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))
                UserInputField(R.string.name_label)
                UserInputField(R.string.email_label, leadingIcon = Icons.Rounded.MailOutline)
                UserInputField(R.string.pass_label, leadingIcon = Icons.Rounded.Lock, true)
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
                        Text(text = stringResource(id = R.string.registration_label),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp)
                    }
                }
            }
        }
    }
}