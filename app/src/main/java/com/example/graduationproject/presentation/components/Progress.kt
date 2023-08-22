package com.example.graduationproject.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressShow() {
    var progress by remember {
        mutableStateOf(0.0f)
    }
    CircularProgressIndicator(
        progress = progress,
        color = Color.Cyan,
        strokeWidth = 10.dp,
        modifier = Modifier.size(100.dp)
    )
    while (progress!=1f){
        progress += (progress+1)/10
    }

}
