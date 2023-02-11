package com.buczi.exchanger.currency.api.data

import javax.validation.constraints.Pattern

data class AccountIdDTO(
    @Pattern(regexp = "^\\d{11}")
    val id: String = "-"
)