package com.example.graduationproject.presentation.ui.theme.activites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.graduationproject.R
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.presentation.components.TextShow
import com.example.graduationproject.presentation.ui.theme.CustomColor
import com.example.graduationproject.presentation.ui.theme.activites.ui.theme.GraduationProjectTheme

class AddToFav : ComponentActivity() {
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraduationProjectTheme {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                       .padding(start = 10.dp, top = 30.dp, end = 10.dp, bottom = 100.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "close activity",
                        modifier = Modifier
                            .clickable {
                                finish()
                                println("finish")
                            }
                            .align(Alignment.End).padding(20.dp)
                    )
                    Card(
                        shape = CardDefaults.outlinedShape,
                        colors = CardDefaults.cardColors(Color(0xFFF8F8F8)),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.Start)
                    ) {
                        TextShow(
                            text = "My Favorites",
                            color = Color(0xff121212),
                            fontFamily = FontFamily.Default,
                            fontSize = 17,
                            weight = 500,
                            modifier = Modifier.padding(20.dp)
                        )

                        LazyColumn(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            items(10) {
                                Row(
                                    Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                ) {
                                    var isCheck by remember {
                                        mutableStateOf(false)
                                    }
                                    GlideImage(
                                        model = SharedObject.url,
                                        contentDescription = "",
                                        modifier = Modifier.size(42.dp)
                                    ) {
                                        it.load(
                                            SharedObject.url
                                        )
                                        it.placeholder(R.drawable.baseline_flag_24)
                                        it.error(R.drawable.baseline_dehaze_24)
                                        it.circleCrop()
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = "USD",
                                            style = TextStyle(
                                                fontSize = 13.49.sp,
                                                lineHeight = 23.12.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins)),
                                                fontWeight = FontWeight(400),
                                                color = Color(0xFF121212),
                                            )
                                        )
                                        Text(
                                            text = "CURRENCY",
                                            style = TextStyle(
                                                fontSize = 11.56.sp,
                                                lineHeight = 19.27.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins)),
                                                fontWeight = FontWeight(400),
                                                color = Color(0xFFB8B8B8),
                                            )
                                        )
                                    }

                                    Spacer(modifier = Modifier.weight(1f))
                                    Card(
                                       border = BorderStroke(1.dp, Color(0xFFB8B8B8)),
                                        colors = CardDefaults.cardColors(Color(0xFFB8B8B8)),
                                        elevation = CardDefaults.cardElevation(0.dp),
                                        shape = RoundedCornerShape((12.dp)),
                                    ) {
                                        Box(modifier = Modifier
                                            .background(
                                                if (isCheck) Color.Black else Color.White
                                            )
                                            .clickable {
                                                isCheck = !isCheck
                                            }
                                            .size(25.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (isCheck)
                                                Icon(
                                                    Icons.Default.Check,
                                                    contentDescription = "",
                                                    tint = Color.White
                                                )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



