package com.example.graduationproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.model.CompareModelGet
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.model.ConvertModel
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

    // main handling room
    val mutableFav = MutableStateFlow(emptyList<CurrencyRoomDBItem>())
    val flow :StateFlow<List<CurrencyRoomDBItem>> = mutableFav
    var list = mutableFav.value
    fun updateFlow() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableFav.value = repository.getAllFav()
            list = mutableFav.value
            println("list is $list")
        }
    }

    suspend fun getAllFav():List<CurrencyRoomDBItem> = repository.getAllFav()
    suspend fun updateRoom(amounts: List<String>, intList: List<Int>){
        withContext(Dispatchers.IO){
            repository.updateRoom(amounts, intList)
        }
    }
    init {
        updateFlow()
    }

    // get list
    private val mutableFlowForList = MutableStateFlow(CurrencyApiList(emptyList()))
    val flowForList: StateFlow<CurrencyApiList> = mutableFlowForList
    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableFlowForList.value = repository.getList()
        }
    }

    // convert
    private val mutableFlowForConvert = MutableStateFlow(ConvertModel(0.0))
    val flowForConvert: StateFlow<ConvertModel> = mutableFlowForConvert
    fun convertCurrecny(
        source: Int,
        target: Int,
        amount: Double
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableFlowForConvert.value = repository.convert(source, target, amount)
        }
    }

    //compare
    private val mutableFlowForCompare = MutableStateFlow(CompareModelGet(emptyList()))
    val flowForCompare: StateFlow<CompareModelGet> = mutableFlowForCompare
    fun compare(compareModelPost: CompareModelPost) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableFlowForCompare.value =
                repository.compare(
                    CompareModelPost(
                        compareModelPost.amount,
                        compareModelPost.baseCurrencyId,
                        compareModelPost.targetCurrencyIds
                    )
                )
            println("${mutableFlowForCompare.value} compare")
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