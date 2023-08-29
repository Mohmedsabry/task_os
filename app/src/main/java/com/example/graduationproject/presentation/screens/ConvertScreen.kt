package com.example.graduationproject.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.graduationproject.R
import com.example.graduationproject.data.model.Currency
import com.example.graduationproject.data.model.CurrencyRoomDBItem
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.presentation.components.DropDownShow
import com.example.graduationproject.presentation.components.TextShow
import com.example.graduationproject.presentation.viewmodels.SharedViewModel
import com.example.graduationproject.ui.theme.CustomColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertScreen(
    compare: (amount: Int, baseId: Int, listToCompare: List<Int>, showLoading: Boolean) -> Unit,
    openAddToFav: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val viewModel = SharedViewModel()
    var amountValue by remember {
        mutableStateOf("")
    }
    var showLoading by remember { mutableStateOf(false) }
    var result by remember {
        mutableStateOf("1")
    }
    var base by remember {
        if (SharedObject.countriesList.isNotEmpty())
            mutableStateOf(SharedObject.countriesList[0])
        else {
            mutableStateOf(Currency("USD", "https://flagcdn.com/h60/us.png", 1))
        }
    }
    var target by remember {
        if (SharedObject.countriesList.isNotEmpty())
            mutableStateOf(SharedObject.countriesList[1])
        else {
            mutableStateOf(Currency("EUR", "https://flagcdn.com/h60/eu.png", 2))
        }
    }
    var favList by remember {
        mutableStateOf(listOf<CurrencyRoomDBItem>())
    }
    var listToCompare = mutableListOf<Int>()
    coroutineScope.launch {
        favList = viewModel.getAllFav()
        listToCompare.clear()
        favList.forEach {
            listToCompare.add(it.id)
        }
        println(listToCompare.size)
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextShow(
                    text = "Amount",
                    color = CustomColor.black,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14
                )
                Spacer(modifier = Modifier.width(130.dp))
                TextShow(
                    text = "From",
                    color = CustomColor.black,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .border(
                            width = 0.5.dp,
                            color = Color(0xFFC5C5C5),
                            RoundedCornerShape(size = 20.dp)
                        )
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .fillMaxWidth(.5f),
                    value = amountValue, onValueChange = {
                        amountValue = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFF000000)),
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Spacer(modifier = Modifier.width(8.dp))
                DropDownShow(
                    base,
                    countryApi = SharedObject.countriesList,
                    modifier = Modifier
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .fillMaxWidth()
                ) { selectedItem ->
                    if (selectedItem == target) {
                        target = base
                        base = selectedItem
                    } else {
                        base = selectedItem
                    }
                }
            }
            Icon(
                painter = painterResource(R.drawable.data_arrow_left_right),
                contentDescription = "Swap Currency",
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 12.dp)
                    .size(35.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        val i = target
                        target = base
                        base = i
                    },
                tint = MaterialTheme.colorScheme.onSurface
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextShow(
                    text = "To",
                    color = CustomColor.black,
                    fontFamily = FontFamily.Default,
                    fontSize = 14
                )
                Spacer(modifier = Modifier.width(175.dp))
                TextShow(
                    text = "Amount",
                    color = CustomColor.black,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                DropDownShow(
                    target,
                    countryApi = SharedObject.countriesList.filter {
                        it.currencyCode != base.currencyCode
                    },
                    modifier = Modifier
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .fillMaxWidth(.5f)
                ) { selectedItem ->
                    target = selectedItem
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .border(
                            width = 0.5.dp,
                            color = Color(0xFFC5C5C5),
                            RoundedCornerShape(size = 20.dp)
                        ),
                    value = result, onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFF000000)),
                    readOnly = false,
                    shape = RoundedCornerShape(20.dp),

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

            }


            Button(
                onClick = {
                    if (amountValue.isNotEmpty() && amountValue.isNotBlank()) {
                        showLoading = true
                        compare.invoke(1, base.id, listToCompare, true)
                        viewModel.viewModelScope.launch(Dispatchers.IO) {
                            viewModel.convertCurrecny(
                                base.id,
                                target.id,
                                amountValue.toDouble()
                            )
                            viewModel.flowForConvert.collectLatest {
                                result = it.conversion_result.toString()
                            }
                        }
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF363636)),
                shape = RoundedCornerShape(size = 20.dp)
            ) {
                Text(
                    text = "Convert",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(800),
                        color = Color.White
                    )
                )
            }
            Divider(
                Modifier
                    .padding(start = 30.dp, end = 30.dp, bottom = 20.dp)
                    .height(1.dp)
                    .background(color = Color(0xFFE9E9E9))
            )
            Row(
                horizontalArrangement = Arrangement.Start,
            ) {
                TextShow(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    text = "live exchange rates",
                    color = Color.Black,
                    fontFamily = FontFamily.Default,
                    fontSize = 18,
                    weight = 700
                )

                Spacer(modifier = Modifier.weight(1f))
                OutlinedButton(
                    onClick = {
                        openAddToFav.invoke()
                    },
                    colors = ButtonDefaults.outlinedButtonColors(Color.White),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(50)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.plus_1),
                        contentDescription = "Add to Favourite", contentScale = ContentScale.None
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    TextShow(
                        text = "Add to Favorites",
                        color = Color(0xFF363636),
                        fontFamily = FontFamily.Default,
                        fontSize = 12
                    )
                }
            }
            TextShow(
                text = "My Portofolio",
                color = Color(0xFF121212),
                fontFamily = FontFamily.Default,
                fontSize = 20,
                weight = 400,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}