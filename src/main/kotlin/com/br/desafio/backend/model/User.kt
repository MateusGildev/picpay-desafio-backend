package com.br.desafio.backend.model

import com.br.desafio.backend.dto.UserDTO
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

@Entity
@Table(name = "tb_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank(message = "Full name must not be blank")
    val fullName: String,

    @field:CPF(message = "Invalid CPF format")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "###.###.###-##")
    val cpf: String,

    @field:Email(message = "Invalid email format")
    @field:NotBlank(message = "Email must not be blank")
    val email: String,

    @field:NotBlank(message = "Password must not be blank")
    val password: String,

    @field:NotNull(message = "Balance must not be null")
    @field:DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than zero")
    var balance: BigDecimal,

    @Enumerated(EnumType.STRING)
    @field:NotNull(message = "Account type must not be null")
    val accountType: AccountType
) {

    constructor(userDTO: UserDTO) : this(
        fullName = userDTO.fullname,
        cpf = userDTO.cpf,
        email = userDTO.email,
        password = userDTO.password,
        balance = userDTO.balance,
        accountType = userDTO.accountType
    )
}


