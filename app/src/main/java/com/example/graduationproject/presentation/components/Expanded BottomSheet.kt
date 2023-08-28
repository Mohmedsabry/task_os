package com.example.graduationproject.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDemo(){

    val bottomSheetScaffoldState= rememberBottomSheetScaffoldState(
      bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope= rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
       sheetShape = RoundedCornerShape(topEnd=5.dp),
        sheetContent = {
            //
        },
        sheetPeekHeight = 0.dp,
        content = {}
    )

}