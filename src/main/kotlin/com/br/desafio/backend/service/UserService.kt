package com.br.desafio.backend.service

import com.br.desafio.backend.dto.UserDTO
import com.br.desafio.backend.exceptions.InsufficientBalance
import com.br.desafio.backend.exceptions.ObjectNotFoundException
import com.br.desafio.backend.exceptions.UnauthorizedTransfer
import com.br.desafio.backend.model.AccountType
import com.br.desafio.backend.model.User
import com.br.desafio.backend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class UserService(

    @Autowired
    val userRepository: UserRepository
) {

    fun newUser(userDTO: UserDTO): User {
        val user = User(userDTO)
        return userRepository.save(user)
        println("Usuario criado! " + user.toString())
    }

    fun findAllUsers(): List<User>? {
        return userRepository.findAll()
    }

    fun findById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
            ?: throw ObjectNotFoundException("O usuário $id não existe! Verifique se o campo está preenchido corretamente.")
    }

    fun findByCpf(cpf: String): User? {
        return userRepository.findByCpf(cpf)
            ?: throw ObjectNotFoundException("CPF: ${cpf} não encontrado! Verfique se o campo esta correto.")
    }

    fun canTransfer(sender: User) {
        if (sender.accountType != AccountType.COMMON) {
            throw UnauthorizedTransfer("Conta do tipo ${sender.accountType} não podem enviar transferencias!")
        }
    }

    fun hasBalance(sender: User, amount: BigDecimal) {
        if (sender.balance < amount) throw InsufficientBalance("Saldo insuficiente para realizar transferencia de ${amount}!")
    }
}