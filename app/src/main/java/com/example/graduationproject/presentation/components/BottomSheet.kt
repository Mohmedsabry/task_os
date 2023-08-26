package com.example.graduationproject.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet() {
    val scope= rememberCoroutineScope()
    val bottomSheetState= rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetState=bottomSheetState,
        sheetContent = {
            //TODO CONTENT here
        },
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetElevation = 12.dp,
        modifier = Modifier.padding(20.dp)
    ) {
MainContent(scope, bottomSheetState)
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(scope:CoroutineScope,bottomSheetState:ModalBottomSheetState){
//Button when click show bottom sheet
    Button(modifier=Modifier.fillMaxWidth(), onClick = {
        scope.launch{
            bottomSheetState.show()
        }
    }
    ){

    }
}