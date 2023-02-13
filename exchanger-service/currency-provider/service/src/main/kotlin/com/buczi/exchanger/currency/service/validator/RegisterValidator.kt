package com.buczi.exchanger.currency.service.validator

import com.buczi.exchanger.currency.api.data.RegisterDTO
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class RegisterValidator {
    companion object {
        private val idRegex = "\\d{11}".toRegex()
    }

    @Throws(RuntimeException::class)
    fun validateRegisterDTO(registerDTO: RegisterDTO) {
        val idValidation = !idRegex.matches(registerDTO.id)
        val ageValidation = registerDTO.age < 18
        if (idValidation) {
            throw RuntimeException("Unable to process registerDTO due to incorrect id should match regex: ^\\d{11}")
        }
        else if (ageValidation) {
            throw RuntimeException("Account cannot be created due to insufficient age: ${registerDTO.age} should be over 18")
        }
    }
}