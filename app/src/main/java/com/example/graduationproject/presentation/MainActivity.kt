package com.example.graduationproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.graduationproject.R
import com.example.graduationproject.presentation.components.DropDownShow
import com.example.graduationproject.presentation.ui.theme.GraduationProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraduationProjectTheme {
                DropDownShow(list = list)
            }
        }
    }
}

val list = listOf("maser" to R.drawable.egypt
    ,"eur" to R.drawable.eur
    ,"flag" to R.drawable.baseline_flag_24)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GraduationProjectTheme {

    }
}