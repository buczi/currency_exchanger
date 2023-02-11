package com.buczi.exchanger.transaction.repository

import com.buczi.exchanger.transaction.data.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface WalletRepository : JpaRepository<Wallet, UUID> {
    fun findAllByOwner(owner: String): List<Wallet>
}