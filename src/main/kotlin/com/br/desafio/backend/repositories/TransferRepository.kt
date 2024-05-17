package com.br.desafio.backend.repositories

import com.br.desafio.backend.model.Transfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransferRepository: JpaRepository<Transfer, Long> {
}