package com.buczi.exchanger.transaction

import com.buczi.exchanger.rate.api.ExchangeRateProviderClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableFeignClients(basePackageClasses = [ExchangeRateProviderClient::class])
@EnableJpaAuditing
@EnableEurekaClient
@SpringBootApplication
class TransactionManagerApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<TransactionManagerApplication>(*args)
        }
    }
}