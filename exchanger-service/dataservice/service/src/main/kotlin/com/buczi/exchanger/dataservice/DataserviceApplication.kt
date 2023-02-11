package com.buczi.exchanger.dataservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DataserviceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<DataserviceApplication>(*args)
        }
    }
}