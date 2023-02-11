package com.buczi.exchanger.rate.provider.nbp

import com.buczi.exchanger.core.log.LoggerDelegate
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class NbpExchangeService(private val nbpRateClient: NbpRateClient) {
    companion object {
        private const val PLN_CODE = "PLN"
    }

    private val logger by LoggerDelegate()

    @Cacheable(value = ["exchange-rates"], key = "{#fromCurrency, #toCurrency}")
    fun getExchangeRateForPLN(fromCurrency: String, toCurrency: String): BigDecimal {
        logger.info("Getting exchange rate from $fromCurrency to $toCurrency")
        return when (PLN_CODE) {
            fromCurrency -> {
                BigDecimal.ONE.divide(nbpRateClient.getExchangeRate(toCurrency), 4, RoundingMode.HALF_UP)
            }
            toCurrency -> {
                nbpRateClient.getExchangeRate(fromCurrency)
            }
            else -> {
                val firstRate = nbpRateClient.getExchangeRate(fromCurrency)
                val secondRate = nbpRateClient.getExchangeRate(toCurrency)
                firstRate.divide(secondRate, 4, RoundingMode.HALF_UP)
            }
        }
    }
}