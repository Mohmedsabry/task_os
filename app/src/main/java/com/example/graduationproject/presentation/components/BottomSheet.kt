package com.example.graduationproject.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BottomSheet(){


ModalBottomSheetLayout(sheetContent = {

},
    sheetShape= RoundedCornerShape(topStart = 8.dp, topEnd =8.dp),
    sheetElevation = 12.dp
) {

}
}
