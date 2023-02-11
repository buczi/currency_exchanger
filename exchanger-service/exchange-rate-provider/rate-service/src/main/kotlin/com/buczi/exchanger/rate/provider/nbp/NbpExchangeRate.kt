package com.buczi.exchanger.rate.provider.nbp

data class NbpExchangeRate(
    val table: String = "",
    val currency: String = "",
    val code: String = "",
    val rates: MutableList<NbpRate> = mutableListOf()
)