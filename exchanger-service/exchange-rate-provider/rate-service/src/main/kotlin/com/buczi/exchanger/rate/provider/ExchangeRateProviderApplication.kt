package com.buczi.exchanger.rate.provider

import com.buczi.exchanger.rate.api.ExchangeRateProviderClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class ExchangeRateProviderApplication {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ExchangeRateProviderApplication>(*args)
        }
    }
}