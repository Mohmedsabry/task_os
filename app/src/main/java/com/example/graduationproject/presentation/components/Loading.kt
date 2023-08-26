package com.example.graduationproject.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loading(isDisplayed: Boolean) {
    if (isDisplayed) {
        Column (modifier=Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){


        Row(
            modifier = Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
CircularProgressIndicator(
    color=MaterialTheme.colorScheme.primary
)
        }
    }
}}