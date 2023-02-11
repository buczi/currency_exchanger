package com.buczi.exchanger.transaction.data

import com.buczi.exchanger.transaction.api.TransactionDTO
import org.springframework.stereotype.Component

@Component
class TransactionDTOMapper {
    fun mapToTransaction(transactionDTO: TransactionDTO): Transaction{
        return Transaction().apply {
            this.walletIdFrom = transactionDTO.walletFrom
            this.walletIdTo = transactionDTO.walletTo
            this.value = transactionDTO.value
        }
    }
}