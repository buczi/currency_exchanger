package com.buczi.exchanger.transaction.controller

import com.buczi.exchanger.transaction.data.Wallet
import com.buczi.exchanger.transaction.service.BalanceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

@RestController
class BalanceController(
    private val balanceService: BalanceService
) {
    @GetMapping("/balance/wallet/{walletId}")
    fun getWalletBalance(@PathVariable("walletId") walletId: String): BigDecimal {
        return balanceService.calculateWalletState(UUID.fromString(walletId))
    }

    @GetMapping("/balance/user/{userId}")
    fun getUserBalance(@PathVariable("userId") userId: String): Map<String, BigDecimal> {
        return balanceService.calculateUserWalletsState(userId)
    }
}