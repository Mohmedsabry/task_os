package com.example.graduationproject.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.presentation.screen.CompareScreen
import com.example.graduationproject.presentation.screens.BaseScreen
import com.example.graduationproject.presentation.screens.ConvertScreen
import com.example.graduationproject.presentation.ui.theme.GraduationProjectTheme
import com.example.graduationproject.presentation.ui.theme.activites.AddToFav

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            GraduationProjectTheme {
                var selectedScreenState by remember {
                    mutableStateOf("Convert")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    BaseScreen() {
                        selectedScreenState = it
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)
                    ) {
                        if (selectedScreenState == "Convert") {
                            ConvertScreen() {
                                startActivity(Intent(context,AddToFav::class.java))
                            }
                        } else {
                            CompareScreen()
                        }
                    }

                }

            }
        }
    }
}

val list = listOf(
    "maser" to SharedObject.url, "eur" to SharedObject.url, "flag" to SharedObject.url
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GraduationProjectTheme {

    }
}