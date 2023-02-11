package com.buczi.exchanger.transaction.data

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "wallet")
class Wallet {
    @Id
    @Column(name = "wallet_id")
    lateinit var walletId: UUID
    lateinit var owner: String
    lateinit var currency: String
}