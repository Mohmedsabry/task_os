package com.example.graduationproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("favourite")
data class CurrencyRoomDBItem(
    val countryFlag: String,
    val countryNameCode: String,
    val currency: String,
    @PrimaryKey
    val id: Int,
    var flag: Boolean = false,
    var amount: String = ""
)
