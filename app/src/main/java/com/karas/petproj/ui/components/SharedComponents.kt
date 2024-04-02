package com.karas.petproj.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karas.petproj.R


@Composable
fun UserInputField(placeHolderID: Int,
                   leadingIcon: ImageVector = Icons.Rounded.AccountCircle,
                   isPassword: Boolean = false,
                   imeAction: ImeAction = ImeAction.Next) {
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
            )
        ),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = if(isPassword) KeyboardType.Password else KeyboardType.Text),
        onValueChange =  {
            textValue.value = it
        },
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
        visualTransformation = if(isShowPassword.value) VisualTransformation.None else PasswordVisualTransformation() )
}

@Composable
fun TitleLabel(resourceID: Int) {
    Text(text = stringResource(id = resourceID),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp)

}


@Composable
@Preview(showSystemUi = true)
fun LoginInputFieldPreview() {
    UserInputField(R.string.login_label)
}