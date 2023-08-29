package com.example.graduationproject.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.graduationproject.data.presestance.SharedObject

class CompareViewModel : ViewModel() {
    var amount by
    mutableStateOf("")
    var firstTargetValue  by
    mutableStateOf("")

    var secondTargetValue by
    mutableStateOf("")

    var firstTargetSelected  by
    mutableStateOf(SharedObject.countriesList[1])

    var secondTargetSelected by
    mutableStateOf(SharedObject.countriesList[2])

    var base by
    mutableStateOf(SharedObject.countriesList[0])

}