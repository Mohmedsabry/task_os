package com.example.graduationproject.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.graduationproject.R
import com.example.graduationproject.presentation.viewmodels.SharedViewModel
import com.example.graduationproject.ui.theme.CustomColor

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Dialog(sharedViewModel: SharedViewModel, dismissAction: () -> Unit) {
    sharedViewModel.getAllListForDialog()
    Dialog(onDismissRequest = {
        dismissAction.invoke()
    }) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 30.dp, bottom = 30.dp, start = 10.dp, end = 10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_close_24),
                contentDescription = "close activity",
                modifier = Modifier
                    .clickable {
                        dismissAction.invoke()
                    }
                    .align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(25.dp))
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
                    modifier = Modifier.padding(top = 29.dp, start = 20.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    items(sharedViewModel.dialogList) { item ->
                        Row(
                            Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            var isCheck by remember {
                                mutableStateOf(item.flag)
                            }
                            GlideImage(
                                model = item.countryFlag,
                                contentDescription = "",
                                modifier = Modifier.size(42.dp)
                            ) {
                                it.load(
                                    item.countryFlag
                                )
                                it.placeholder(R.drawable.baseline_flag_24)
                                it.error(R.drawable.baseline_dehaze_24)
                                it.circleCrop()
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.Start
                            ) {
                                TextShow(
                                    text = item.currency,
                                    color = CustomColor.black,
                                    fontFamily = FontFamily.Default,
                                    fontSize = 13
                                )
                                TextShow(
                                    text = "CURRENCY",
                                    color = Color(0xFFB8B8B8),
                                    fontFamily = FontFamily.Default,
                                    fontSize = 13
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))
                            Box(modifier = Modifier
                                .clickable {
                                    isCheck = !isCheck
                                }
                                .size(25.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isCheck) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.grop),
                                        contentDescription = "check",
                                        modifier = Modifier.size(30.dp)
                                    )
                                } else {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ellipse),
                                        contentDescription = "check",
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                                if (isCheck) {
                                    sharedViewModel.insertRoom(item)
                                    sharedViewModel.getAllFav()
                                } else {
                                    sharedViewModel.deleteRoom(item)
                                    sharedViewModel.getAllFav()
                                }
                            }
                        }
                        Divider(
                            Modifier
                                .background(color = Color(0xFFB9C1D9))
                                .weight(1f)
                        )
                    }
                }
            }
        }
    }

}