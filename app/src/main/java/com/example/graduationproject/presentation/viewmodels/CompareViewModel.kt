package com.example.graduationproject.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.model.CompareModelGet
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompareViewModel : ViewModel() {
    val repository = Repository()
    var amount by
    mutableStateOf("")
    var firstTargetValue by
    mutableStateOf("")

    var secondTargetValue by
    mutableStateOf("")

    var firstTargetSelected by
    mutableStateOf(SharedObject.countriesList[1])

    var secondTargetSelected by
    mutableStateOf(SharedObject.countriesList[2])

    var base by
    mutableStateOf(SharedObject.countriesList[0])

    var showLoading by mutableStateOf(false)
    var compareResult by mutableStateOf(CompareModelGet(emptyList()))

    fun compare(compareModelPost: CompareModelPost) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading = true
            compareResult = repository.compare(
                CompareModelPost(
                    compareModelPost.amount,
                    compareModelPost.baseCurrencyId,
                    compareModelPost.targetCurrencyIds
                )
            )
            firstTargetValue =
                compareResult.compare_result[0].toString()
            secondTargetValue =
                compareResult.compare_result[1].toString()
            showLoading = false
        }
    }
}