package com.buczi.exchanger.transaction.service

import com.buczi.exchanger.transaction.data.Wallet
import com.buczi.exchanger.transaction.repository.TransactionRepository
import com.buczi.exchanger.transaction.repository.WalletRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class BalanceService(
    private val transactionRepository: TransactionRepository,
    private val walletRepository: WalletRepository
) {
    fun calculateWalletState(walletId: UUID): BigDecimal {
        val accumulatedSum = transactionRepository.findAllByWalletIdTo(walletId).sumOf { it.value }
        val spentSum = transactionRepository.findAllByWalletIdFrom(walletId).sumOf { it.preValue }
        return accumulatedSum - spentSum
    }

    fun calculateUserWalletsState(userId: String): Map<String, BigDecimal> {
        return walletRepository.findAllByOwner(userId)
            .associate { it.currency to calculateWalletState(it.walletId) }
    }
}