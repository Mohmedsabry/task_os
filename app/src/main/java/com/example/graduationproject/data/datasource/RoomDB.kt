package com.example.graduationproject.data.datasource

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.graduationproject.data.model.CurrencyRoomDBItem
import com.example.graduationproject.data.presestance.SharedObject

@Database(
    entities = [CurrencyRoomDBItem::class],
    version = 1,
)
abstract class RoomDB : RoomDatabase() {
    companion object {
        fun getInstance(): RoomDB {
            return Room.databaseBuilder(
                SharedObject.appContext,
                RoomDB::class.java, "mydataBase"
            ).build()
        }
    }

    abstract fun getDao(): Dao
}