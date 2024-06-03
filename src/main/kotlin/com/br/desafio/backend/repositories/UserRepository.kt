package com.br.desafio.backend.repositories

import com.br.desafio.backend.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByCpf(cpf: String): User?
}