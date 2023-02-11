package com.buczi.exchanger.rate.provider.controller

import com.buczi.exchanger.rate.provider.nbp.NbpExchangeService
import com.buczi.exchanger.rate.provider.nbp.NbpRateClient
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.ws.rs.QueryParam

@RestController
class ExchangeRateController(private val nbpExchangeService: NbpExchangeService) {
    @Operation(method = "Fetch current exchange rate between two currencies")
    @GetMapping(value=["/rate/{fromCurrency}/{toCurrency}"])
    fun getCurrentRate(
        @PathVariable("fromCurrency") fromCurrency: String,
        @PathVariable("toCurrency") toCurrency: String
    ): BigDecimal {
        return nbpExchangeService.getExchangeRateForPLN(fromCurrency, toCurrency)
    }

    @GetMapping(value=["/rate/timed/{fromCurrency}/{toCurrency}"])
    fun getRateForPointInTime(
        @PathVariable("fromCurrency") fromCurrency: String,
        @PathVariable("toCurrency") toCurrency: String,
        @QueryParam("time") localDateTime: LocalDateTime
    ): BigDecimal {
        return BigDecimal.ZERO
    }
}