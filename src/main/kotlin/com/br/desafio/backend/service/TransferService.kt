package com.br.desafio.backend.service

import com.br.desafio.backend.dto.TransferDTO
import com.br.desafio.backend.exceptions.UserNotFound
import com.br.desafio.backend.model.Transfer
import com.br.desafio.backend.repositories.TransferRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class TransferService(

    @Autowired
    val transferRepository: TransferRepository,

    @Autowired
    val userService: UserService
) {
    fun findAllTransfers(): List<Transfer>? {
        return transferRepository.findAll()
    }

    @Transactional
    fun createTransfer(transferDTO: TransferDTO): Transfer {
        val sender = checkNotNull(userService.findById(transferDTO.sender))
        { throw UserNotFound("O usuário ${transferDTO.sender} não existe! Verifique se o campo está preenchido corretamente.") }
        val receiver = checkNotNull(userService.findById(transferDTO.receiver))
        { throw UserNotFound("O usuário ${transferDTO.sender} não existe! Verifique se o campo está preenchido corretamente.") }

        userService.canTransfer(sender)
        userService.hasBalance(sender, transferDTO.amount)
        sender.balance = sender.balance.subtract(transferDTO.amount)
        receiver.balance = receiver.balance.plus(transferDTO.amount)

        val transfer: Transfer =
            Transfer(null, transferDTO.sender, transferDTO.receiver, transferDTO.amount, LocalDateTime.now())
        val returned = transferRepository.save(transfer)
        val personalizadoReturn = "A transferência de R$${transfer.amount} foi realizada com sucesso!\n" +
                "Pagador: ${(sender.fullName)}\n" +
                "Favorecido: ${(receiver).fullName}"
        return returned
    }

    fun findById(id: Long): Optional<Transfer>? {
        if (id == null){
            return null
        }

        return transferRepository.findById(id)
    }
}