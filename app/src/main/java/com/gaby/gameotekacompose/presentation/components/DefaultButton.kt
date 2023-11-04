package com.gaby.gameotekacompose.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButton(
    text: String,
    errorMsg: String = "",
    onClick: () -> Unit,
    color: Color = Color(0xFF018786),
    icon: ImageVector = Icons.Default.ArrowForward,
    enable: Boolean = true
) {
    Column {

        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 45.dp, horizontal = 30.dp),
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(color),
            enabled = enable

        ) {
            Icon(imageVector = icon, contentDescription = "")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text)
        }

        Text(
            text = errorMsg,
            modifier = Modifier.padding(top = 5.dp),
            fontSize = 11.sp,
            color = Color.Red
        )


    }

}

