package com.br.desafio.backend.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "tb_transfer")
data class Transfer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val sender: Long,
    val receiver: Long,
    val amount: BigDecimal,
    val date: LocalDateTime
) {

}
