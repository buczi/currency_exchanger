package com.buczi.exchanger.eureka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
class EurekaServerApplication{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<EurekaServerApplication>(*args)
        }
    }
}

