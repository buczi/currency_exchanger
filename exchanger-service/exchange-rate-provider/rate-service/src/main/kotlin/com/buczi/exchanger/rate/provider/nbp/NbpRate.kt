package com.buczi.exchanger.rate.provider.nbp

import java.time.LocalDate

data class NbpRate(
    val no: String,
    val effectiveDate: LocalDate,
    val mid: String
)