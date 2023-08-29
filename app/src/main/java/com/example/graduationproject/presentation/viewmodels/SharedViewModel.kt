package com.example.graduationproject.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.model.CompareModelGet
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.model.Currency
import com.example.graduationproject.data.model.CurrencyApiList
import com.example.graduationproject.data.model.CurrencyRoomDBItem
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// main 3 fun
class SharedViewModel : ViewModel() {
    private val repository = Repository()
    private val mutableFlowForList = MutableStateFlow(CurrencyApiList(emptyList()))
    val flowForList: StateFlow<CurrencyApiList> = mutableFlowForList
    var showLoading by mutableStateOf(false)
    var convertState by mutableDoubleStateOf(0.0)
    var compareResult by mutableStateOf(CompareModelGet(emptyList()))
    var favList by mutableStateOf(listOf<CurrencyRoomDBItem>())
    var listToCompare = mutableListOf<Int>()
    var selectedScreenState by mutableStateOf("Convert")
    var showBottomSheet by mutableStateOf(false)


    // main handling room

    suspend fun insertRoom(currencyRoomDBItem: CurrencyRoomDBItem) {
        repository.insertRoom(currencyRoomDBItem)
    }

    suspend fun deleteRoom(currencyRoomDBItem: CurrencyRoomDBItem) {
        repository.deleteRoom(currencyRoomDBItem)
    }

    suspend fun getAllFav() {
        favList = repository.getAllFav()
        listToCompare.clear()
        favList.forEach {
            listToCompare.add(it.id)
        }
        println(listToCompare.size)
    }

    suspend fun updateRoom() {
        withContext(Dispatchers.IO) {
            val list = mutableListOf<String>()
            list.clear()
            compareResult.compare_result.forEach {
                list.add(it.toString())
            }
            println("k $list $listToCompare")
            if (list.size == listToCompare.size)
                repository.updateRoom(list, listToCompare)
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
            showLoading = false
        }
    }

    //compare
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
            showLoading = false
        }
    }

    // dealing with room
    suspend fun getAllListForBottomSheet(): List<CurrencyRoomDBItem> {
        // list from Api
        val list = convertFromApiListToRoomList(SharedObject.countriesList)
        val favList = repository.getAllFav()
        list.forEach { apiItem ->
            favList.forEach { converted ->
                if (converted.id == apiItem.id) apiItem.flag = true
            }
        }
        return list
    }

    private fun convertFromApiListToRoomList(list: List<Currency>): List<CurrencyRoomDBItem> {
        val backList = mutableListOf<CurrencyRoomDBItem>()
        list.forEach {
            backList.add(CurrencyRoomDBItem(it.flagUrl, it.currencyCode, it.id))
        }
        return backList
    }
}