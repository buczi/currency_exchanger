package com.buczi.exchanger.transaction.controller

import com.buczi.exchanger.transaction.api.TransactionDTO
import com.buczi.exchanger.transaction.data.TransactionDTOMapper
import com.buczi.exchanger.transaction.service.TransactionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TransactionController(
    private val transactionService: TransactionService,
    private val transactionDTOMapper: TransactionDTOMapper
) {
    @PostMapping("/transaction/execute")
    fun executeTransaction(@Valid @RequestBody transactionDTO: TransactionDTO) {
        val transaction = transactionDTOMapper.mapToTransaction(transactionDTO)
        transactionService.execute(transaction)
    }
}