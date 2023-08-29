package com.example.graduationproject.presentation.screens

import StateToggle
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BaseScreen(
    selectedScreen: (stateText: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        Alignment.TopEnd
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "logo", contentScale = ContentScale.Crop,
            modifier = Modifier
                .alpha(1f)
                .fillMaxWidth().size(220.dp)
                .padding(bottom = 8.dp),
        )
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, top = 15.dp),
            text = "ConCurrency",
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                color = Color.White,
            )
        )
        StateToggle(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {
            selectedScreen.invoke(it)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp, top = 50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Currency Converter", style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(900),
                    color = Color.White,
                )
            )
            Text(
                text = "Check live foreign currency exchange rates", style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(750),
                    color = Color.LightGray,
                )
            )
        }

    }

}

