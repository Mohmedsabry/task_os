package com.example.graduationproject.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun TextShow(
    text: String,
    color: Color,
    fontSize: Int = 22,
    weight: Int = 600,
    fontFamily: FontFamily
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight(weight),
            color = color,
        )
    )
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun preview() {


}