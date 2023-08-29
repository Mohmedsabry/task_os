package com.example.graduationproject.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.model.Currency
import com.example.graduationproject.data.model.CurrencyApiList
import com.example.graduationproject.data.model.CurrencyRoomDBItem
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// main 3 fun
class SharedViewModel : ViewModel() {
    private val repository = Repository()
    private val mutableFlowForList = MutableStateFlow(CurrencyApiList(emptyList()))
    val flowForList: StateFlow<CurrencyApiList> = mutableFlowForList
    var showLoading by mutableStateOf(false)
    var convertState by mutableDoubleStateOf(0.0)
    var favList by mutableStateOf(listOf<CurrencyRoomDBItem>())
    var listToCompare by mutableStateOf(listOf<Int>())
    var selectedScreenState by mutableStateOf("Convert")
    var showDialog by mutableStateOf(false)
    var dialogList by mutableStateOf(listOf<CurrencyRoomDBItem>())

    // main handling room
    init {
        getAllFav()
    }

    fun insertRoom(currencyRoomDBItem: CurrencyRoomDBItem) {
        viewModelScope.launch {
            repository.insertRoom(currencyRoomDBItem)
        }
    }

    fun deleteRoom(currencyRoomDBItem: CurrencyRoomDBItem) {
        viewModelScope.launch {
            repository.deleteRoom(currencyRoomDBItem)
        }
    }

    fun getAllFav() {
        viewModelScope.launch {
            favList = repository.getAllFav()
            listToCompare = favList.map {
                it.id
            }
        }
    }


    // get list

    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableFlowForList.value = repository.getList()
        }
    }

    // convert
    fun convert(baseId: Int, targetId: Int, amountValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading = true
            convertState = repository.convert(
                source = baseId,
                target = targetId,
                amount = amountValue.toDouble()
            ).conversion_result
            val compareResult1 = repository.compare(
                CompareModelPost(
                    1,
                    baseId,
                    listToCompare
                )
            ).compare_result
            favList = favList.mapIndexed { index, item ->
                item.copy(amount = compareResult1[index].toString())
            }
            showLoading = false
        }
    }


    // dealing with room
    fun getAllListForDialog() {
        // list from Api
        viewModelScope.launch {
            val list = convertFromApiListToRoomList(SharedObject.countriesList)
            val favList = repository.getAllFav()
            list.forEach { apiItem ->
                favList.forEach { converted ->
                    if (converted.id == apiItem.id) apiItem.flag = true
                }
            }
            dialogList = list
        }
    }

    private fun convertFromApiListToRoomList(list: List<Currency>): List<CurrencyRoomDBItem> {
        val backList = mutableListOf<CurrencyRoomDBItem>()
        list.forEach {
            backList.add(CurrencyRoomDBItem(it.flagUrl, it.currencyCode, it.id))
        }
        return backList
    }
}