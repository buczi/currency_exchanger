package com.buczi.exchanger.currency.service.controller

import com.buczi.exchanger.currency.api.data.AccountIdDTO
import com.buczi.exchanger.currency.api.data.RegisterDTO
import com.buczi.exchanger.currency.service.data.RegisterDTOMapper
import com.buczi.exchanger.currency.service.repository.AccountRepository
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AccountController(
    private val accountRepository: AccountRepository,
    private val registerDTOMapper: RegisterDTOMapper
) {

    @PostMapping("/account/create")
    fun createAccount(@Valid registerDTO: RegisterDTO) {
        val account = registerDTOMapper.mapToAccount(registerDTO)
        accountRepository.save(account)
    }

    @PatchMapping("/account/deactivate")
    fun removeAccount(@Valid @RequestBody accountIdDTO: AccountIdDTO) {
        accountRepository.findById(accountIdDTO.id).orElseThrow()
            .apply { this.active = false }
            .run(accountRepository::save)
    }
}