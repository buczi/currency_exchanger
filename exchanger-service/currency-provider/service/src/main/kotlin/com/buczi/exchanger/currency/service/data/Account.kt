package com.buczi.exchanger.currency.service.data

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "account")
class Account {
    @Id
    lateinit var id: String
    lateinit var name: String
    lateinit var surname: String
    var age: Int = 0
    var active: Boolean = true
}