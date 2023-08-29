package com.example.graduationproject.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.graduationproject.R
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.model.Currency
import com.example.graduationproject.data.model.CurrencyRoomDBItem
import com.example.graduationproject.domain.Repository
import com.example.graduationproject.presentation.components.DailogShow
import com.example.graduationproject.presentation.components.Loading
import com.example.graduationproject.presentation.components.TextShow
import com.example.graduationproject.presentation.screens.BaseScreen
import com.example.graduationproject.presentation.screens.CompareScreen
import com.example.graduationproject.presentation.screens.ConvertScreen
import com.example.graduationproject.presentation.viewmodels.SharedViewModel
import com.example.graduationproject.ui.theme.CustomColor
import com.example.graduationproject.ui.theme.GraduationProjectTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val repository = Repository()
            val coroutineScope = rememberCoroutineScope()
            val viewModel = SharedViewModel()
            var showLoading by remember {
                mutableStateOf(false)
            }
            GraduationProjectTheme {
                var favList by remember {
                    mutableStateOf(listOf<CurrencyRoomDBItem>())
                }
                var listToCompare = mutableListOf<Int>()
                viewModel.viewModelScope.launch {
                    viewModel.flowForCompare.collectLatest {
                        val list = mutableListOf<String>()
                        list.clear()
                        it.compare_result.forEach {
                            list.add(it.toString())
                        }
                        if (list.size == listToCompare.size)
                            viewModel.updateRoom(list, listToCompare)
                        favList = viewModel.getAllFav()
                        println("hi ${it.compare_result.size} ${listToCompare.size}")
                    }
                }
                coroutineScope.launch {
                    favList = viewModel.getAllFav()
                    listToCompare.clear()
                    favList.forEach {
                        listToCompare.add(it.id)
                    }
                }
                var selectedScreenState by remember {
                    mutableStateOf("Convert")
                }
                var showBottomSheet by remember {
                    mutableStateOf(false)
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        item {
                            BaseScreen() {
                                selectedScreenState = it
                            }
                        }
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (selectedScreenState == "Convert") {
                                    ConvertScreen({ amount, baseId, listToCompare, showload ->
                                        showLoading = showload
                                        coroutineScope.launch {
                                            delay(1000)
                                            showLoading = false
                                        }
                                        viewModel.viewModelScope.launch(Dispatchers.IO) {
                                            viewModel.compare(
                                                CompareModelPost(
                                                    1,
                                                    baseId,
                                                    listToCompare
                                                )
                                            )
                                        }
                                    }) {
                                        showBottomSheet = true
                                    }
                                } else {
                                    CompareScreen { showload ->
                                        showLoading = showload
                                        coroutineScope.launch {
                                            delay(1000)
                                            showLoading = false
                                        }
                                    }
                                }

                            }
                        }
                        item {
                            AnimatedVisibility(visible = showBottomSheet) {
                                DailogShow(repository) {
                                    showBottomSheet = false
                                    viewModel.viewModelScope.launch {
                                        favList = viewModel.getAllFav()
                                        listToCompare.clear()
                                        favList.forEach {
                                            listToCompare.add(it.id)
                                        }
                                    }
                                }
                            }
                        }
                        if (selectedScreenState == "Convert") {
                            items(favList.size) { index ->
                                Row(
                                    Modifier
                                        .padding(start = 24.dp, end = 24.dp, top = 16.dp)
                                        .fillMaxWidth()
                                ) {
                                    GlideImage(
                                        model = favList[index].countryFlag,
                                        contentDescription = "image of currency",
                                        modifier = Modifier.size(42.dp)
                                    ) {
                                        it.load(
                                            favList[index].countryFlag
                                        )
                                        it.placeholder(R.drawable.baseline_flag_24)
                                        it.error(R.drawable.baseline_dehaze_24)
                                        it.circleCrop()
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        TextShow(
                                            text = favList[index].currency,
                                            color = CustomColor.black,
                                            fontFamily = FontFamily.Default,
                                            fontSize = 15
                                        )
                                        TextShow(
                                            text = "CURRENCY",
                                            color = Color(0xFFB8B8B8),
                                            fontFamily = FontFamily.Default,
                                            fontSize = 11
                                        )
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    TextShow(
                                        text = favList[index].amount,
                                        color = Color(0xFF121212),
                                        fontFamily = FontFamily.Default
                                    )
                                }
                            }
                        }
                    }
                }
            }
            if (showLoading) {
                println("$showLoading")
                Loading()
            }
        }
    }


}

val list = listOf(
    Currency(flagUrl = "https://flagcdn.com/h60/us.png", currencyCode = "USA", id = 1),
    Currency(flagUrl = "https://flagcdn.com/h60/eu.png", currencyCode = "EUR", id = 2),
    Currency(flagUrl = "https://flagcdn.com/h60/gb.png", currencyCode = "UK", id = 3),
)




