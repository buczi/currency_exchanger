package com.buczi.exchanger.currency.service.data

import com.buczi.exchanger.currency.api.data.RegisterDTO
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RegisterDTOMapper {
    fun mapToAccount(registerDTO: RegisterDTO): Account {
        return Account().apply {
            this.id = registerDTO.id
            this.name = registerDTO.name
            this.surname = registerDTO.surname
            this.age = registerDTO.age
        }
    }
}