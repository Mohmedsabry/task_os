package com.example.graduationproject.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.graduationproject.R
import com.example.graduationproject.data.model.Currency
import com.example.graduationproject.domain.Repository
import com.example.graduationproject.presentation.components.Loading
import com.example.graduationproject.presentation.components.TextShow
import com.example.graduationproject.presentation.components.dailogShow
import com.example.graduationproject.presentation.screens.BaseScreen
import com.example.graduationproject.presentation.screens.CompareScreen
import com.example.graduationproject.presentation.screens.ConvertScreen
import com.example.graduationproject.presentation.viewmodels.CompareViewModel
import com.example.graduationproject.presentation.viewmodels.ConvertViewModel
import com.example.graduationproject.presentation.viewmodels.SharedViewModel
import com.example.graduationproject.ui.theme.CustomColor
import com.example.graduationproject.ui.theme.GraduationProjectTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val repository = Repository()
            val convertViewModel by viewModels<ConvertViewModel>()
            val compareViewModel by viewModels<CompareViewModel>()
            val coroutineScope = rememberCoroutineScope()
            val sharedViewModel by viewModels<SharedViewModel>()
            GraduationProjectTheme {
                sharedViewModel.viewModelScope.launch {
                    sharedViewModel.getAllFav()
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
                                sharedViewModel.selectedScreenState = it
                            }
                        }
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (sharedViewModel.selectedScreenState == "Convert") {
                                    ConvertScreen(
                                        sharedViewModel,
                                        convertViewModel,
                                        compareViewModel,
                                    ) {
                                        sharedViewModel.showBottomSheet = true
                                    }
                                } else {
                                    CompareScreen(compareViewModel, sharedViewModel)
                                }
                            }
                        }
                        item {
                            AnimatedVisibility(visible = sharedViewModel.showBottomSheet) {
                                dailogShow(repository) {
                                    sharedViewModel.showBottomSheet = false
                                    sharedViewModel.viewModelScope.launch {
                                        sharedViewModel.getAllFav()
                                        sharedViewModel.listToCompare.clear()
                                        sharedViewModel.favList.forEach {
                                            sharedViewModel.listToCompare.add(it.id)
                                        }
                                    }
                                }
                            }
                        }
                        if (sharedViewModel.selectedScreenState == "Convert") {
                            items(sharedViewModel.favList) { listItem ->
                                Row(
                                    Modifier
                                        .padding(start = 24.dp, end = 24.dp, top = 16.dp)
                                        .fillMaxWidth()
                                ) {
                                    GlideImage(
                                        model = listItem.countryFlag,
                                        contentDescription = "image of currency",
                                        modifier = Modifier.size(42.dp)
                                    ) {
                                        it.load(
                                            listItem.countryFlag
                                        )
                                        it.placeholder(R.drawable.baseline_flag_24)
                                        it.error(R.drawable.baseline_dehaze_24)
                                        it.circleCrop()
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        TextShow(
                                            text = listItem.currency,
                                            color = CustomColor.black,
                                            fontFamily = FontFamily.Default,
                                            fontSize = 15
                                        )
                                        TextShow(
                                            text = "Currency",
                                            color = Color(0xFFB8B8B8),
                                            fontFamily = FontFamily.Default,
                                            fontSize = 13
                                        )
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    TextShow(
                                        text = listItem.amount,
                                        color = Color(0xFF121212),
                                        fontFamily = FontFamily.Default
                                    )
                                }
                            }
                        }
                    }
                }
            }
            if (sharedViewModel.showLoading) {
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GraduationProjectTheme {

    }
}
// dimensionResource(id = R.dimen._17sdp)