package com.buczi.exchanger.rate.api

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.math.BigDecimal

@FeignClient("exchange-rate-provider")
interface ExchangeRateProviderClient {
    @RequestMapping(method = [RequestMethod.GET], value = ["/rate/{fromCurrency}/{toCurrency}"])
    fun getCurrentRate(
        @PathVariable("fromCurrency") fromCurrency: String,
        @PathVariable("toCurrency") toCurrency: String
    ): BigDecimal
}