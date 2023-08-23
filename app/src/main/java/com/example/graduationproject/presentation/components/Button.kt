package com.example.graduationproject.presentation.components

import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily


@Composable
fun ButtonShow(text: String, background: Color, color: Color, onClickedButton: () -> Unit) {
    Button(
        onClick = {
            onClickedButton.invoke()
        }, modifier = Modifier
    ) {
        Text(text = text, style = TextStyle(color))
    }
}

@Composable
fun FloatingButton(text: String, background: Color, color: Color, onClickedButton: () -> Unit) {
    FloatingActionButton(onClick = { onClickedButton() },Modifier.background(background)) {
        TextShow(text = text, color = color, fontFamily = FontFamily.Default)
    }
}