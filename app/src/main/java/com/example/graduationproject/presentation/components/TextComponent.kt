package com.example.graduationproject.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TextShow(
    text: String,
    color: Long = Color.White.value.toLong(),
    fontSize: Int = 300,
    weight: Int = 600
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = FontFamily(Font(R.font.open)),
            fontWeight = FontWeight(weight),
            color = Color(color),
        )
    )
}