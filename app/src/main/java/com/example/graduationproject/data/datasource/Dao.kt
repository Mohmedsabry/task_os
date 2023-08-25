package com.example.graduationproject.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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
}