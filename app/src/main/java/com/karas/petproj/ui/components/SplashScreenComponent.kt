package com.karas.petproj.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.karas.petproj.R
import com.karas.petproj.navigation.NavigationItem


@Composable
fun SplashScreen(navHostController: NavHostController) {
    Surface(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()) {

        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))
            Image(Icons.Rounded.Notifications, contentDescription = "", modifier = Modifier
                .width(300.dp)
                .height(300.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))
            ActionButton(textID = R.string.register_button_action, {
                navHostController.navigate(NavigationItem.Registration.route)
            })
            ActionButton(textID = R.string.login_label, {
                navHostController.navigate(NavigationItem.Login.route)
            })
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
//    SplashScreen()
}