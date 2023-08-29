package com.example.graduationproject.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.presentation.components.DropDownShow
import com.example.graduationproject.presentation.components.TextShow
import com.example.graduationproject.presentation.viewmodels.CompareViewModel
import com.example.graduationproject.presentation.viewmodels.SharedViewModel
import com.example.graduationproject.ui.theme.CustomColor

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareScreen(
    compareViewModel: CompareViewModel,
    sharedViewModel: SharedViewModel,
) {
    Column(Modifier.padding(30.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
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
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .background(
                        color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp)
                    ),
                value = compareViewModel.amount,
                onValueChange = {
                    compareViewModel.amount = it
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFF000000)),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                shape = RoundedCornerShape(20.dp)
            )
            Spacer(
                modifier = Modifier
                    .width(10.dp)
                    .background(
                        color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp)
                    )
            )
            DropDownShow(
                compareViewModel.base,
                countryApi = SharedObject.countriesList,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp)
                    )
            ) { selectedItem ->
                when (selectedItem) {
                    compareViewModel.firstTargetSelected -> {
                        compareViewModel.firstTargetSelected = compareViewModel.base
                        compareViewModel.base = selectedItem
                    }

                    compareViewModel.secondTargetSelected -> {
                        compareViewModel.secondTargetSelected = compareViewModel.base
                        compareViewModel.base = selectedItem
                    }

                    else -> {
                        compareViewModel.base = selectedItem
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
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
                compareViewModel.firstTargetSelected,
                countryApi = SharedObject.countriesList.filter {
                    it.currencyCode != compareViewModel.base.currencyCode && it.currencyCode != compareViewModel.secondTargetSelected.currencyCode
                },
                modifier = Modifier.fillMaxWidth(.5f)
            ) { selectedItem ->
                compareViewModel.firstTargetSelected = selectedItem
            }
            Spacer(modifier = Modifier.width(10.dp))
            DropDownShow(
                compareViewModel.secondTargetSelected,
                countryApi = SharedObject.countriesList.filter {
                    it.currencyCode != compareViewModel.base.currencyCode && it.currencyCode != compareViewModel.firstTargetSelected.currencyCode
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp)
                    )
            ) {

            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
        ) {
            OutlinedTextField(
                value = compareViewModel.firstTargetValue, onValueChange = {}, readOnly = false,
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .border(
                        width = 0.5.dp, color = Color(0xFFC5C5C5), RoundedCornerShape(size = 20.dp)
                    )
                    .background(
                        color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp)
                    ),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFF000000)),
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = compareViewModel.secondTargetValue, onValueChange = {}, readOnly = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp, color = Color(0xFFC5C5C5), RoundedCornerShape(size = 20.dp)
                    )
                    .background(
                        color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp)
                    ),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFF000000)),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if (compareViewModel.amount.isNotEmpty() && compareViewModel.amount.isNotBlank()) {
                    compareViewModel.compare(
                        CompareModelPost(
                            compareViewModel.amount.toInt(),
                            compareViewModel.base.id,
                            listOf(
                                compareViewModel.firstTargetSelected.id,
                                compareViewModel.secondTargetSelected.id
                            )
                        )
                    )

                }
            }, Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                CustomColor.black, contentColor = Color.White
            )
        ) {
            Text(text = "Compare", fontSize = 16.sp, fontWeight = FontWeight(800))
        }
    }
}