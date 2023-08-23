package com.example.graduationproject.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R
import com.example.graduationproject.presentation.components.DropDownShow
import com.example.graduationproject.presentation.components.TextShow
import com.example.graduationproject.presentation.ui.theme.CustomColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareScreen() {
    var amount by remember {
        mutableStateOf("")
    }
    var target1 by remember {
        mutableStateOf("")
    }
    var target2 by remember {
        mutableStateOf("")
    }
    Column(Modifier.padding(30.dp)) {
        Row( modifier = Modifier.fillMaxWidth()) {
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
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(10.dp),
                value = amount, onValueChange = {
                    amount = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            DropDownShow(
                list = list, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
        TextShow(
            text = "To",
            color = CustomColor.black,
            fontFamily = FontFamily.Default,
            fontSize = 14
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DropDownShow(
                list = list, modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(10.dp)
            )
            TextShow(
                text = "or",
                color = CustomColor.black,
                fontFamily = FontFamily.Default,
                fontSize = 10
            )
            Spacer(modifier = Modifier.padding(10.dp))
            DropDownShow(
                list = list, modifier = Modifier.fillMaxWidth()
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(
                value = target1, onValueChange = {}, enabled = false,
                modifier = Modifier.fillMaxWidth(.5f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = target2, onValueChange = {}, enabled = false,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = {

            },
            Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(CustomColor.compareButtonColor, contentColor = Color.White)
        ) {
            Text(text = "Compare", fontSize = 16.sp)
        }
    }
}

val list = listOf("maser" to R.drawable.egypt, "flag" to R.drawable.baseline_flag_24)