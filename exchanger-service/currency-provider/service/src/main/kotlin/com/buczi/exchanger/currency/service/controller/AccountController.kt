package com.buczi.exchanger.currency.service.controller

import com.buczi.exchanger.currency.api.data.AccountIdDTO
import com.buczi.exchanger.currency.api.data.RegisterDTO
import com.buczi.exchanger.currency.service.validator.RegisterValidator
import com.buczi.exchanger.currency.service.data.RegisterDTOMapper
import com.buczi.exchanger.currency.service.repository.AccountRepository
import com.buczi.exchanger.transaction.api.WalletClient
import com.buczi.exchanger.transaction.api.WalletCreationDTO
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RestController
class AccountController(
    private val accountRepository: AccountRepository,
    private val registerDTOMapper: RegisterDTOMapper,
    private val registerValidator: RegisterValidator,
    private val walletClient: WalletClient
) {

    @PostMapping("/account/create")
    fun createAccount(@Valid @RequestBody registerDTO: RegisterDTO) {
        registerValidator.validateRegisterDTO(registerDTO)
        val account = registerDTOMapper.mapToAccount(registerDTO)
        accountRepository.save(account)
        registerDTO.activeWallets.forEach {
            val walletCreationDTO = WalletCreationDTO(
                ownerId = registerDTO.id,
                currency = it,
                startingBalance = registerDTO.initialValue
            )
            walletClient.getCurrentRate(walletCreationDTO)
        }
    }

    @PatchMapping("/account/deactivate")
    fun removeAccount(@Valid @RequestBody accountIdDTO: AccountIdDTO) {
        accountRepository.findById(accountIdDTO.id).orElseThrow()
            .apply { this.active = false }
            .run(accountRepository::save)
    }
}