package com.example.graduationproject.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.model.CurrencyRoomDBItem

@Dao
interface Dao {
    @Insert
    suspend fun insert(currencyRoomDBItem: CurrencyRoomDBItem)

    @Delete
    suspend fun delete(currencyRoomDBItem: CurrencyRoomDBItem)

    @Query("select * from favourite group by id")
    suspend fun getAllFav(): List<CurrencyRoomDBItem>

    @Query("select * from favourite where id =:id")
    suspend fun getFavById(id: Int): CurrencyRoomDBItem

    @Query("update favourite set amount =:amount where id=:id")
    fun updateRoom(amount:String,id:Int)
     fun updateList(amounts:List<String>,intList:List<Int>){
        for(i in amounts.indices){
            updateRoom(amounts[i],intList[i])
        }
    }
}