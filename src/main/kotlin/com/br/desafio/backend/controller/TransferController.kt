package com.br.desafio.backend.controller

import com.br.desafio.backend.dto.TransferDTO
import com.br.desafio.backend.model.Transfer
import com.br.desafio.backend.service.TransferService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/transfer")
class TransferController(

    @Autowired
    val transferService: TransferService
) {

    @GetMapping
    @Operation(summary = "Find All Transfers")
    fun findAllTransfers(): ResponseEntity<List<Transfer>> {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.findAllTransfers())
    }

    @PostMapping
    @Operation(summary = "Realizar Transferencia entre Users")
    fun createTransfer(@RequestBody @Valid transferDTO: TransferDTO):ResponseEntity<Transfer>{
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(transferDTO))
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Transfer by Id")
    fun findById(@PathVariable id:Long): ResponseEntity<Optional<Transfer>> {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.findById(id))
    }
}