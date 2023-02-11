package com.buczi.exchanger.currency.service.repository

import com.buczi.exchanger.currency.service.data.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, String> {
}