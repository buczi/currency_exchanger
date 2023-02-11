package com.buczi.exchanger.transaction.data

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Id

@Entity(name = "transaction")
@EntityListeners(AuditingEntityListener::class)
class Transaction {
    @Id
    @Column(name = "transaction_id")
    var transactionId: UUID = UUID.randomUUID()

    @Column(name = "last_transaction_id")
    lateinit var lastTransactionId: UUID

    @Column(name = "wallet_id_from")
    lateinit var walletIdFrom: UUID

    @Column(name = "wallet_id_to")
    lateinit var walletIdTo: UUID

    @Column(name = "pre_value")
    lateinit var preValue: BigDecimal

    lateinit var value: BigDecimal

    @CreatedDate
    @Column(name = "date_created", updatable = false)
    lateinit var dateCreated: LocalDateTime
}