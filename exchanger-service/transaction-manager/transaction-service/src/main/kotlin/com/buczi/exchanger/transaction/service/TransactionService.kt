package com.buczi.exchanger.transaction.service

import com.buczi.exchanger.core.log.LoggerDelegate
import com.buczi.exchanger.rate.api.ExchangeRateProviderClient
import com.buczi.exchanger.transaction.data.Transaction
import com.buczi.exchanger.transaction.repository.TransactionRepository
import com.buczi.exchanger.transaction.repository.WalletRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val walletRepository: WalletRepository,
    private val exchange: ExchangeRateProviderClient
) {
    companion object {
        private const val maxRetryCount = 3
    }

    private val logger by LoggerDelegate()

    fun execute(transaction: Transaction) {
        var retryCount = 0
        val fromWallet = walletRepository.findById(transaction.walletIdFrom).orElseThrow()
        val toWallet = walletRepository.findById(transaction.walletIdTo).orElseThrow()

        val value = if (fromWallet.currency == toWallet.currency) {
            transaction.value
        } else {
            transaction.value * exchange.getCurrentRate(fromWallet.currency, toWallet.currency)
        }

        transaction.apply {
            this.preValue = this.value
            this.value = value
        }

        while (retryCount < maxRetryCount) {
            try {
                val lastTransactionId =
                    transactionRepository.findTransactionByLastTransactionIdOrderByDateCreated(transaction.walletIdFrom)
                lastTransactionId?.let {
                    transaction.apply {
                        this.lastTransactionId = it.transactionId
                    }
                } ?: transaction.apply {
                    this.lastTransactionId = this.transactionId
                }

                transactionRepository.save(transaction)
                return
            } catch (e: Exception) {
                logger.warn("Unable to close transaction ${transaction.transactionId} caused by exception: ${e.message}")
            } finally {
                retryCount++
            }
        }
    }
}