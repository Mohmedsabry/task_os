package com.example.graduationproject.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.model.Currency
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConvertViewModel : ViewModel() {
    var amountValue by mutableStateOf("")

    var base by
    if (SharedObject.countriesList.isNotEmpty()) mutableStateOf(SharedObject.countriesList[0])
    else {
        mutableStateOf(Currency("USD", "https://flagcdn.com/h60/us.png", 1))
    }
    var target by
    if (SharedObject.countriesList.isNotEmpty()) mutableStateOf(SharedObject.countriesList[1])
    else {
        mutableStateOf(Currency("EUR", "https://flagcdn.com/h60/eu.png", 2))
    }


}