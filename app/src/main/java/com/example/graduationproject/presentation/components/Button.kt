package com.example.graduationproject.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ButtonShow(text: String, color: Color, onClickedButton: () -> Unit) {
    Button(
        onClick = {
            onClickedButton.invoke()
        }, modifier = Modifier.background(color)
    ) {
        TextShow(text = text)
    }
}