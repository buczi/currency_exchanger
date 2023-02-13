package com.buczi.exchanger.currency.api.data

import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class RegisterDTO(
    @field:NotBlank val name: String,
    @field:NotBlank val surname: String,
    @Min(18) val age: Int,
    @field:Pattern(regexp = "^\\d{11}") val id: String,
    val initialValue: BigDecimal = BigDecimal.ZERO,
    val activeWallets: List<String> = listOf("PLN")
)