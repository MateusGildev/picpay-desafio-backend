package com.br.desafio.backend.dto

import com.br.desafio.backend.model.AccountType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

class UserDTO(
    @NotBlank
    val fullname: String,
    @NotBlank
    val cpf: String,
    @NotBlank
    val email: String,
    @NotBlank
    val password: String,
    @NotNull
    val balance: BigDecimal,
    @NotNull
    val accountType: AccountType
) {

}