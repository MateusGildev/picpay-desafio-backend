package com.br.desafio.backend.exceptions

class ObjectNotFoundException : RuntimeException {

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(message: String) : super(message)

    companion object {
        private const val SerialVersionUID: Long = 1L
    }


}