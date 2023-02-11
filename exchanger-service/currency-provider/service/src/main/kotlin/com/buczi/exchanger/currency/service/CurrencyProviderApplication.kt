package com.buczi.exchanger.currency.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class CurrencyProviderApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<CurrencyProviderApplication>(*args)
        }
    }
}