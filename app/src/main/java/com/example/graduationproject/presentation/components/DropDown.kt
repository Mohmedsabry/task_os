package com.example.graduationproject.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.graduationproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownShow(list: List<Pair<String, Int>>) {
    var text by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = {},
            enabled = false,
            trailingIcon = {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "drop down")
            },
            modifier = Modifier.clickable {
                expanded = expanded.not()
            }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
        }) {
            list.forEach { item ->
                DropdownMenuItem(text = {
                    Row {
                        Text(text = item.first)
                        Divider(Modifier.width(2.dp), color = Color.Black)
                        Icon(painter = painterResource(id = item.second), contentDescription = "")
                    }
                }, onClick = {
                    text = item.first
                })
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Perview() {

}