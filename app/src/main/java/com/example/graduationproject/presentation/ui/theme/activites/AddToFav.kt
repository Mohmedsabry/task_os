package com.example.graduationproject.presentation.ui.theme.activites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                        .padding(20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "close activity",
                        modifier = Modifier
                            .clickable {
                                finish()
                                println("finish")
                            }
                            .align(Alignment.End)
                    )
                    Card(
                        shape = CardDefaults.outlinedShape,
                        colors = CardDefaults.cardColors(Color(0xffB8B8B8)),
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
                                    var checked by remember {
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
                                        TextShow(
                                            text = "Usa",
                                            color = CustomColor.black,
                                            fontFamily = FontFamily.Default,
                                            fontSize = 13
                                        )
                                        TextShow(
                                            text = "Currancy",
                                            color = CustomColor.black,
                                            fontFamily = FontFamily.Default,
                                            fontSize = 11
                                        )
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Checkbox(
                                        checked = checked,
                                        onCheckedChange = { checked = it },
                                        Modifier,
                                        colors = CheckboxDefaults.colors(Color(0xff363636))
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


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GraduationProjectTheme {
        Greeting("Android")
    }
}