package com.br.desafio.backend.controller.exceptions

import com.br.desafio.backend.exceptions.InsufficientBalance
import com.br.desafio.backend.exceptions.ObjectNotFoundException
import com.br.desafio.backend.exceptions.UnauthorizedTransfer
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException::class)
    fun handleObjectNotFoundException(
        ex: ObjectNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {

        val error = StandardError(
            timestamp = System.currentTimeMillis(),
            status = HttpStatus.NOT_FOUND.value().toString(), //retorna 404
            message = ex.message ?: "Object not found",
            errorType = "Object not found",
            path = request.requestURI
        )

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }

    @ExceptionHandler(InsufficientBalance::class)
    fun handleInsufficientBalanceException(
        ex: InsufficientBalance,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {

        val error = StandardError(
            timestamp = System.currentTimeMillis(),
            status = HttpStatus.BAD_REQUEST.reasonPhrase.toString(),
            message = ex.message ?: "Insufficient balance",
            errorType = "Insufficient balance",
            path = request.requestURI
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }

    @ExceptionHandler(UnauthorizedTransfer::class)
    fun handleUnauthorizedTransferException(
        ex: UnauthorizedTransfer,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {

        val error = StandardError(
            timestamp = System.currentTimeMillis(),
            status = HttpStatus.BAD_REQUEST.value().toString(), //retorna  "Not found"
            message = ex.message ?: "Invalid account Type",
            errorType = "Invalid account Type",
            path = request.requestURI
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }
}