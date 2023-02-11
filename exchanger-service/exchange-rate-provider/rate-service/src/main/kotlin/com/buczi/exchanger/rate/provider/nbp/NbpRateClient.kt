package com.buczi.exchanger.rate.provider.nbp

import com.buczi.exchanger.rate.provider.nbp.exception.NbpRateNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.time.LocalDate

@Component
class NbpRateClient(private val restTemplate: RestTemplate) {
    @Value("\${exchange.rate.nbp.url}")
    lateinit var nbpURL: String

    fun getExchangeRate(currencyCode: String): BigDecimal {
        return restTemplate.getForObject("$nbpURL/exchangerates/rates/A/$currencyCode/", NbpExchangeRate::class.java)
            ?.let { exchangeRate ->
                exchangeRate.rates
                    .first { it.effectiveDate == LocalDate.now().minusDays(1) }
                    .run { BigDecimal(this.mid) }
            } ?: throw NbpRateNotFoundException(currencyCode)
    }
}