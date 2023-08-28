package com.example.graduationproject.presentation.components

//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Loading(isDisplayed: Boolean,modifier: Modifier) {
    if (isDisplayed) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier
        )
    }
}