package com.buczi.exchanger.rate.provider.nbp.exception

class NbpRateNotFoundException(currencyCode: String): RuntimeException("Unable to find exchange rate for code: $currencyCode") {
}