package com.example.graduationproject.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var expanded by remember { mutableStateOf(false) }
    var text = currency.currencyCode
    var url = currency.flagUrl
    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor(),
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
                shape = RoundedCornerShape(20.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
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
}