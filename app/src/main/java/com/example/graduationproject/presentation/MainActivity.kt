package com.example.graduationproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.graduationproject.presentation.screen.CompareScreen
import com.example.graduationproject.presentation.ui.theme.GraduationProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraduationProjectTheme {
                CompareScreen()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GraduationProjectTheme {

    }
}