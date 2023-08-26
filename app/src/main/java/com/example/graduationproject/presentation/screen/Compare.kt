package com.example.graduationproject.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.presentation.components.DropDownShow
import com.example.graduationproject.presentation.components.Loading
import com.example.graduationproject.presentation.components.TextShow
import com.example.graduationproject.presentation.ui.theme.CustomColor
import com.example.graduationproject.presentation.viewmodels.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareScreen() {
    val viewModel = SharedViewModel()
    val coroutineScope = rememberCoroutineScope()

    var amount by remember {
        mutableStateOf("")
    }
    var targetValue1 by remember {
        mutableStateOf("")
    }
    var targetValue2 by remember {
        mutableStateOf("")
    }
    var targetSelected1 by remember {
        mutableStateOf(SharedObject.countriesList[1])
    }
    var targetSelected2 by remember {
        mutableStateOf(SharedObject.countriesList[2])
    }
    var base by remember {
        mutableStateOf(SharedObject.countriesList[0])
    }
    var showLoading by remember {
        mutableStateOf(false)
    }
    if (showLoading) {
        Loading(isDisplayed = showLoading)
        coroutineScope.launch {
            delay(3000)
            showLoading = false
        }
    } else {
        Column(Modifier.padding(30.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextShow(
                    text = "Amount",
                    color = CustomColor.black,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14
                )
                Spacer(modifier = Modifier.width(135.dp))
                TextShow(
                    text = "From",
                    color = CustomColor.black,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        ),
                    value = amount, onValueChange = {
                        amount = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFF000000)),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = RoundedCornerShape(20.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(10.dp)
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                )
                DropDownShow(
                    base,
                    countryApi = SharedObject.countriesList, modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                ) { selectedItem ->
                    when (selectedItem) {
                        targetSelected1 -> {
                            targetSelected1 = base
                            base = selectedItem
                        }

                        targetSelected2 -> {
                            targetSelected2 = base
                            base = selectedItem
                        }

                        else -> {
                            base = selectedItem
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextShow(
                    text = "Targeted currency",
                    color = CustomColor.black,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14
                )
                Spacer(modifier = Modifier.width(40.dp))
                TextShow(
                    text = "Targeted currency",
                    color = CustomColor.black,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                DropDownShow(
                    targetSelected1,
                    countryApi = SharedObject.countriesList.filter {
                        it.currencyCode != base.currencyCode && it.currencyCode != targetSelected2.currencyCode
                    }, modifier = Modifier
                        .fillMaxWidth(.5f)
                ) { selectedItem ->
                    targetSelected1 = selectedItem
                }
                Spacer(modifier = Modifier.width(10.dp))
                DropDownShow(
                    targetSelected2,
                    countryApi = SharedObject.countriesList.filter {
                        it.currencyCode != base.currencyCode && it.currencyCode != targetSelected1.currencyCode
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                ) { selectedItem ->

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    value = targetValue1, onValueChange = {}, enabled = false,
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFF000000)),
                )
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedTextField(
                    value = targetValue2, onValueChange = {}, enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(size = 20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFF000000)),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if(amount.isNotEmpty()&&amount.isNotBlank()) {
                        showLoading=true
                    viewModel.viewModelScope.launch(Dispatchers.IO) {
                        viewModel.compare(
                            CompareModelPost(
                                amount.toInt(),
                                base.id,
                                listOf(targetSelected1.id, targetSelected2.id)
                            )
                        )
                        viewModel.flowForCompare.collectLatest {
                            if (it.compare_result.isNotEmpty()) {
                                targetValue1 = it.compare_result[0].toString()
                                targetValue2 = it.compare_result[1].toString()
                            }
                        }
                    }}
                },
                Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    CustomColor.black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Compare", fontSize = 16.sp, fontWeight = FontWeight(800))
            }
        }
    }


}