package com.example.graduationproject.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(text = "Amount")

            var isOpen by remember { mutableStateOf(false) }
            var text by remember { mutableStateOf("") }
            if (isOpen) {
                TextField(
                    value = text,
                    onValueChange = { newValue ->
                        text = newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            } else {
                Text(
                    text = "1 EGP", color = Color.Gray, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { isOpen = true }
                )
            }

            Text(text = "From")

          //DropDownShow(Modifier)

            Text(text = "To")

            Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween) {
                //     DropDownShow(modifier = Modifier.weight(1f))
                //  DropDownShow(modifier = Modifier.weight(1f))
            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(text = "Convert")
            }

        }
    }

}