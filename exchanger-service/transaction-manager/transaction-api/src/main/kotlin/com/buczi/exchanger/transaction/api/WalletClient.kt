package com.buczi.exchanger.transaction.api

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("transaction-service")
interface WalletClient {
    @RequestMapping(method = [RequestMethod.POST], value = ["/walet/create"])
    fun getCurrentRate(
        @RequestBody walletCreationDTO: WalletCreationDTO
    )
}