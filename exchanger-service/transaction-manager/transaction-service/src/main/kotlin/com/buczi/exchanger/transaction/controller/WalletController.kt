package com.buczi.exchanger.transaction.controller

import com.buczi.exchanger.transaction.api.WalletCreationDTO
import com.buczi.exchanger.transaction.data.Transaction
import com.buczi.exchanger.transaction.data.Wallet
import com.buczi.exchanger.transaction.repository.WalletRepository
import com.buczi.exchanger.transaction.service.TransactionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

@RestController
class WalletController(
    private val walletRepository: WalletRepository,
    private val transactionService: TransactionService
) {

    @PostMapping("/wallet/create")
    fun createWallet(@RequestBody walletCreationDTO: WalletCreationDTO) {
        val wallet = createWalletFromDTO(walletCreationDTO)
        val transaction = createBaseTransaction(wallet, walletCreationDTO.startingBalance)
        walletRepository.save(wallet)
        transactionService.execute(transaction)
    }

    private fun createWalletFromDTO(walletCreationDTO: WalletCreationDTO): Wallet {
        return Wallet().apply {
            this.owner = walletCreationDTO.ownerId
            this.currency = walletCreationDTO.currency
            this.walletId = UUID.randomUUID()
        }
    }

    private fun createBaseTransaction(wallet: Wallet, value: BigDecimal): Transaction {
        val baseWallet = walletRepository.findAllByOwner("0000000000")
            .find { it.currency == wallet.currency }

        return Transaction().apply {
            this.walletIdFrom = baseWallet!!.walletId
            this.walletIdTo = wallet.walletId
            this.value = value
        }
    }
}