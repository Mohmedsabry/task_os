package com.example.graduationproject.domain

import com.example.graduationproject.data.datasource.RoomDB
import com.example.graduationproject.data.model.CompareModelGet
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.model.ConvertModel
import com.example.graduationproject.data.model.CurrencyApiList
import com.example.graduationproject.data.model.CurrencyRoomDBItem
import com.example.graduationproject.data.network.NetworkService
import com.example.graduationproject.data.presestance.SharedObject

class Repository {
    private val roomDao = RoomDB.getInstance().getDao()
    private val retrofit = SharedObject.getInstanceOfRetrofit()
    private val networkService = retrofit.create(NetworkService::class.java)

    //retrofit
    suspend fun convert(
        source: Int,
        target: Int,
        amount: Double
    ): ConvertModel = networkService.convert(source, target, amount)

    suspend fun getList(): CurrencyApiList = networkService.getList()
    suspend fun compare(compareModelPost: CompareModelPost): CompareModelGet {
        return networkService.compare(compareModelPost)
    }


    // room DB
    suspend fun updateRoom(amounts: List<String>, intList: List<Int>) {
        roomDao.updateList(amounts, intList)
    }

    suspend fun insertRoom(currencyRoomDBItem: CurrencyRoomDBItem) {
        if (this.getFavById(currencyRoomDBItem.id) == null) {
            currencyRoomDBItem.flag = true
            roomDao.insert(currencyRoomDBItem)
        }
    }

    suspend fun deleteRoom(currencyRoomDBItem: CurrencyRoomDBItem) {
        roomDao.delete(currencyRoomDBItem)
    }

    suspend fun getAllFav(): List<CurrencyRoomDBItem> = roomDao.getAllFav()
    suspend fun getFavById(id: Int): CurrencyRoomDBItem? = roomDao.getFavById(id)

}