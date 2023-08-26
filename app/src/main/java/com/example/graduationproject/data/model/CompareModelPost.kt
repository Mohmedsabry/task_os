package com.example.graduationproject.data.model

data class CompareModelPost(
    val amount: Int,
    val baseCurrencyId: Int,
    val targetCurrencyIds: List<Int>
)