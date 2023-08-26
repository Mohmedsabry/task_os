package com.example.graduationproject.data

import com.example.graduationproject.data.datasource.RoomDB
import com.example.graduationproject.data.model.ConvertModel
import com.example.graduationproject.data.model.CurrencyApiItem
import com.example.graduationproject.data.model.CurrencyRoomDBItem
import com.example.graduationproject.data.network.NetworkService
import com.example.graduationproject.data.presestance.SharedObject
import com.example.graduationproject.presentation.list

class Repository {
    private val roomDao = RoomDB.getInstance().getDao()
    private val retrofit = SharedObject.getInstanceOfRetrofit()
    private val networkService = retrofit.create(NetworkService::class.java)
     //retrofit
    suspend fun convert():ConvertModel = networkService.convert()
    suspend fun getList():List<CurrencyApiItem> = networkService.getList()

    // room DB
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

  suspend fun getAllListForBottomSheet(): List<CurrencyRoomDBItem> {
        //list from Api
         val list = convertFromApiListToRoomList(listApi)
          val favList = getAllFav()
        list.forEach { apiItem ->
         favList.forEach { converted ->
           if (converted.id == apiItem.id) apiItem.flag = true
         }
         }
         return list

}
}


    private fun convertFromApiListToRoomList(list: List<CurrencyApiItem>): List<CurrencyRoomDBItem> {
        val backList = mutableListOf<CurrencyRoomDBItem>()
        list.forEach {
            backList.add(CurrencyRoomDBItem(it.countryFlag, it.countryNameCode, it.currency, it.id))
        }
        return backList
    }

   val listApi = listOf(
       CurrencyApiItem("https://flagcdn.com/h60/us.png", "USA", "USD", 1),
        CurrencyApiItem("https://flagcdn.com/h60/eu.png", "EUR", "EUR", 2),
        CurrencyApiItem("https://flagcdn.com/h60/gb.png", "UK", "GBP", 3),
    )


