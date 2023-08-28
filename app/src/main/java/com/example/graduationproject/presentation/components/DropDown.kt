package com.example.graduationproject.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.graduationproject.R
import com.example.graduationproject.data.model.Currency

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun DropDownShow(
    currency: Currency,
    countryApi: List<Currency>,
    modifier: Modifier = Modifier,
    selectedItem: (selectText: Currency) -> Unit
) {

    var text = currency.currencyCode
    var url = currency.flagUrl
    var expanded by remember {
        mutableStateOf(false)
    }
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = {},
            enabled = false,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "drop down",
                    Modifier.clickable {
                        expanded = expanded.not()
                    }
                )
            },
            leadingIcon = {
                GlideImage(
                    model = url,
                    contentDescription = "currency image",
                    Modifier.size(30.dp)
                ) {
                    it.load(url)
                    it.placeholder(R.drawable.baseline_flag_24)
                    it.error(R.drawable.baseline_error_outline_24)
                    it.circleCrop()
                }
            },
            modifier = modifier,
            shape = RoundedCornerShape(20.dp)
        )
        DropdownMenu(
            expanded = expanded, onDismissRequest = {
                expanded = false
            }, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            countryApi.forEach { item ->
                DropdownMenuItem(text = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GlideImage(
                            model = item.flagUrl,
                            contentDescription = "",
                            Modifier.size(30.dp)
                        ) {
                            it.load(item.flagUrl)
                            it.placeholder(R.drawable.baseline_flag_24)
                            it.error(R.drawable.baseline_error_outline_24)
                            it.circleCrop()
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = item.currencyCode)
                    }
                }, onClick = {
                    text = item.currencyCode
                    selectedItem.invoke(item)
                    url = item.flagUrl
                    expanded = false
                })
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Perview() {

}