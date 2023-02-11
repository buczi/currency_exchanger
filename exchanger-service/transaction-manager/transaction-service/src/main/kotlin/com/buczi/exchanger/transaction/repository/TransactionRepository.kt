package com.buczi.exchanger.transaction.repository

import com.buczi.exchanger.transaction.api.TransactionDTO
import com.buczi.exchanger.transaction.data.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID> {
    fun findAllByWalletIdFrom(walletIdFrom: UUID): List<Transaction>
    fun findAllByWalletIdTo(walletIdTo: UUID): List<Transaction>
    fun findTransactionByLastTransactionIdOrderByDateCreated(walletIdFrom: UUID): Transaction?
}