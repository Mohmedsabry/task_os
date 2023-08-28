//package com.example.graduationproject.presentation.screens
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewModelScope
//import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
//import com.bumptech.glide.integration.compose.GlideImage
//import com.example.graduationproject.R
//import com.example.graduationproject.data.model.CompareModelPost
//import com.example.graduationproject.data.model.CurrencyRoomDBItem
//import com.example.graduationproject.presentation.components.Loading
//import com.example.graduationproject.presentation.components.TextShow
//import com.example.graduationproject.presentation.screen.CompareScreen
//import com.example.graduationproject.presentation.viewmodels.SharedViewModel
//import com.example.graduationproject.ui.theme.CustomColor
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalGlideComposeApi::class)
//@Composable
//fun MainScreen() {
//    val viewModel = SharedViewModel()
//    var selectedScreenState by remember {
//        mutableStateOf("Convert")
//    }
//    var showBottomSheet by remember {
//        mutableStateOf(false)
//    }
//    var showLoading by remember {
//        mutableStateOf(false)
//    }
//    var favList by remember {
//        mutableStateOf(listOf<CurrencyRoomDBItem>())
//    }
//    var listToCompare = mutableListOf<Int>()
//    LaunchedEffect(key1 = Unit) {
//        viewModel.flowFav.collectLatest {
//            favList = it
//            listToCompare.clear()
//            favList.forEach {
//                listToCompare.add(it.id)
//            }
//            println(listToCompare.size)
//        }
//    }
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        item {
//            BaseScreen {
//                selectedScreenState = it
//            }
//        }
//        if (showLoading) {
//            item {
//                Loading(isDisplayed = true)
//            }
//        } else {
//            if (selectedScreenState == "Convert") {
//                item {
//                    ConvertScreen({
//                        viewModel.viewModelScope.launch(Dispatchers.IO) {
//                            viewModel.compare(CompareModelPost(1, it.id, listToCompare))
//                            viewModel.flowForCompare.collectLatest {
//                                val list = mutableListOf<String>()
//                                list.clear()
//                                it.compare_result.forEach {
//                                    list.add(it.toString())
//                                }
//                                viewModel.updateRoom(list, listToCompare)
//                                viewModel.flowFav.collectLatest {
//                                    favList = it
//                                    println("hi $it $list")
//                                }
//                            }
//                        }
//                    }) {
//                        showBottomSheet = true
//                    }
//                }
//            } else {
//                item {
//                    CompareScreen()
//                }
//            }
//        }
//        item {
//            TextShow(
//                text = "My Portofolio",
//                color = Color(0xFF121212),
//                fontFamily = FontFamily.Default,
//                fontSize = 20,
//                weight = 400,
//                modifier = Modifier.padding(10.dp)
//            )
//        }
//        items(favList.size) { index ->
//            Row(
//                Modifier
//                    .padding(10.dp)
//                    .fillMaxWidth()
//            ) {
//                GlideImage(
//                    model = favList[index].countryFlag,
//                    contentDescription = "image of currency",
//                    modifier = Modifier.size(42.dp)
//                ) {
//                    it.load(
//                        favList[index].countryFlag
//                    )
//                    it.placeholder(R.drawable.baseline_flag_24)
//                    it.error(R.drawable.baseline_dehaze_24)
//                    it.circleCrop()
//                }
//                Spacer(modifier = Modifier.width(10.dp))
//                Column {
//                    TextShow(
//                        text = favList[index].currency,
//                        color = CustomColor.black,
//                        fontFamily = FontFamily.Default,
//                        fontSize = 15
//                    )
//                    TextShow(
//                        text = "Currency",
//                        color = Color(0xFFB8B8B8),
//                        fontFamily = FontFamily.Default,
//                        fontSize = 13
//                    )
//                }
//                Spacer(modifier = Modifier.weight(1f))
//                TextShow(
//                    text = favList[index].amount,
//                    color = Color(0xFF121212),
//                    fontFamily = FontFamily.Default
//                )
//            }
//        }
//    }
//}