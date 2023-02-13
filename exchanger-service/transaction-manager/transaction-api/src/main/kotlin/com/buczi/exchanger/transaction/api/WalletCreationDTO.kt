package com.buczi.exchanger.transaction.api

import java.math.BigDecimal

data class WalletCreationDTO(
    val ownerId: String,
    val currency: String,
    val startingBalance: BigDecimal
)