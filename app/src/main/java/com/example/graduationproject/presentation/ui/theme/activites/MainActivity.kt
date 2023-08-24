package com.example.graduationproject.presentation

import StateToggle
import android.os.Bundle
import android.service.controls.templates.StatelessTemplate
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.graduationproject.R
import com.example.graduationproject.presentation.components.DropDownShow
import com.example.graduationproject.presentation.screen.CompareScreen
import com.example.graduationproject.presentation.screens.BaseScreen
import com.example.graduationproject.presentation.screens.ConvertScreen
import com.example.graduationproject.presentation.ui.theme.GraduationProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GraduationProjectTheme {


                var selectedScreenState by remember {
                    mutableStateOf("Convert")
                }
                //  DropDownShow(list = list)
                Column(
                    modifier = Modifier.fillMaxSize().background(Color.Yellow)
                ) {
                    BaseScreen() {
                        selectedScreenState = it
                    }
                    Box(
                        modifier = Modifier
                            .background(Color.Gray)
                            .fillMaxWidth()
                            .weight(1F)
                    ) {
                        if (selectedScreenState == "Convert") {
                            ConvertScreen()
                        } else {
                            CompareScreen()
                        }
                    }

                }

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