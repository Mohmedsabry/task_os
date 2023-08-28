package com.example.graduationproject.presentation.components

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.graduationproject.R
import com.example.graduationproject.data.Repository
import com.example.graduationproject.data.model.CurrencyRoomDBItem
import com.example.graduationproject.presentation.ui.theme.CustomColor
import com.example.graduationproject.presentation.viewmodels.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@SuppressLint("CoroutineCreationDuringComposition")
@Preview
@OptIn(ExperimentalMaterialApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun BottomSheet() {
    val repository=Repository()
    val scope= rememberCoroutineScope()
    val bottomSheetState= rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetState=bottomSheetState,
        sheetContent = {
            //TODO CONTENT here
            val coroutineScope = rememberCoroutineScope()
            var list2 by remember {
                mutableStateOf(listOf<CurrencyRoomDBItem>())
            }
            val viewModel: SharedViewModel= SharedViewModel()
            viewModel.viewModelScope.launch {
                list2 = viewModel.getAllListForBottomSheet()
            }
          //  Dialog(onDismissRequest = {
              //  dismissAction.invoke()
           // })
   // {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "close activity",
                        modifier = Modifier
                            .clickable {
                          //      dismissAction.invoke()
                            }
                            .align(Alignment.End)
                            .padding(bottom = 20.dp)
                    )
                    Card(
                        shape = CardDefaults.outlinedShape,
                        colors = CardDefaults.cardColors(CustomColor.lightGray),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.Start)
                    ) {
                        TextShow(
                            text = "My Favorites",
                            color = Color(0xff121212),
                            fontFamily = FontFamily.Default,
                            fontSize = 17,
                            weight = 500,
                            modifier = Modifier.padding(20.dp)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            items(list2.size) { index ->
                                Row(
                                    Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                ) {
                                    var isCheck by remember {
                                        mutableStateOf(list2[index].flag)
                                    }
                                    GlideImage(
                                        model = list2[index].countryFlag,
                                        contentDescription = "",
                                        modifier = Modifier.size(42.dp)
                                    ) {
                                        it.load(
                                            list2[index].countryFlag
                                        )
                                        it.placeholder(R.drawable.baseline_flag_24)
                                        it.error(R.drawable.baseline_dehaze_24)
                                        it.circleCrop()
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        TextShow(
                                            text = list2[index].currency,
                                            color = CustomColor.black,
                                            fontFamily = FontFamily.Default,
                                            fontSize = 13
                                        )
                                        TextShow(
                                            text = list2[index].currency,
                                            color = CustomColor.black,
                                            fontFamily = FontFamily.Default,
                                            fontSize = 11
                                        )
                                    }

                                    Spacer(modifier = Modifier.weight(1f))
                                    Card(
                                        colors = CardDefaults.cardColors(Color.Black),
                                        elevation = CardDefaults.cardElevation(0.dp),
                                        shape = RoundedCornerShape((12.dp)),
                                    ) {
                                        Box(modifier = Modifier
                                            .background(
                                                if (isCheck) Color.Black else Color.LightGray
                                            )
                                            .clickable {
                                                isCheck = !isCheck
                                            }
                                            .size(25.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (isCheck)
                                                Icon(
                                                    Icons.Default.Check,
                                                    contentDescription = "check",
                                                    tint = Color.White
                                                )
                                        }
                                        if (isCheck) {
                                            coroutineScope.launch(Dispatchers.IO) {
                                                repository.insertRoom(list2[index])
                                                println("${repository.getAllFav().size} insert")
                                            }
                                        } else {
                                            coroutineScope.launch(Dispatchers.IO) {
                                                repository.deleteRoom(list2[index])
                                                println("${repository.getAllFav().size} delete")
                                            }
                                        }
                                        coroutineScope.launch {
                                            println("${repository.getFavById(list2[index].id)} get item")
                                        }

                                    }
                                }
                            }
                        }
                    }

            }
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
@Preview
@Composable
fun Test(){

}