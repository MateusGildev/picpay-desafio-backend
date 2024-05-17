package com.br.desafio.backend.dto

import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

class TransferDTO(
    @NotNull
    val sender: Long,
    @NotNull
    val receiver: Long,
    @NotNull
    val amount: BigDecimal

) {

}