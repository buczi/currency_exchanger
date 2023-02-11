package com.buczi.exchanger.transaction.api

import java.math.BigDecimal
import java.util.UUID
import javax.validation.constraints.Min

data class TransactionDTO(
    val walletFrom: UUID,
    val walletTo: UUID,
    @Min(0)
    val value: BigDecimal
)