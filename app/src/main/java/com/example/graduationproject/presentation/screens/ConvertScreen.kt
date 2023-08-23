package com.example.graduationproject.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R
import com.example.graduationproject.presentation.components.DropDownShow
import com.example.graduationproject.presentation.components.TextShow
import com.example.graduationproject.presentation.list
import com.example.graduationproject.presentation.ui.theme.CustomColor


@Preview()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyCard(
) {
    var amountValue by remember {
        mutableStateOf("1")
    }

    var result by remember {
        mutableStateOf("1")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
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
                    .fillMaxWidth(.4f),
                value = amountValue, onValueChange = {
                    amountValue = it
                },
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            Spacer(modifier = Modifier.width(8.dp))
            DropDownShow(
                list = list, modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 40.dp)
                .clip(CircleShape)
                .clickable {
                }
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_sync_24),
                contentDescription = "Swap Currency",
                modifier = Modifier
                    .padding(8.dp)
                    .size(25.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
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
            Spacer(modifier = Modifier.width(130.dp))
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
                list = list, modifier = Modifier
                    .fillMaxWidth(.4f)

            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = result, onValueChange = {},
                enabled = false,
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

        }

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF363636)),
            shape = RoundedCornerShape(size = 20.dp)
        ) {
            Text(
                text = "Convert",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color =Color.White
                )
            )
        }

    }
}

