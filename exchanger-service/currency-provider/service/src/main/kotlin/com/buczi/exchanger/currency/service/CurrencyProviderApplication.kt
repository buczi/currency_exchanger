package com.buczi.exchanger.currency.service

import com.buczi.exchanger.transaction.api.WalletClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients(basePackageClasses = [WalletClient::class])
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