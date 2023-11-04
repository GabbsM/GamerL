package com.gaby.gameotekacompose.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
    modifier: Modifier,
    errorMsg: String = "",
    value: String,
    onValueChange: (value: String) -> Unit,
    validateField: () -> Unit = {},
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false
) {

    Column {

        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {

                onValueChange(it)
                validateField()
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            maxLines = 1,
            label = { Text(label) },
            leadingIcon = {
                Icon(
                    imageVector = icon, contentDescription = ""
                )
            },
            visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None

        )

        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = errorMsg,
            fontSize = 11.sp,
            color = Color.Red
        )
    }

}
