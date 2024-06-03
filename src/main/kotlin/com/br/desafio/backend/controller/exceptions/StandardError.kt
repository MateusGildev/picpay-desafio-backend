package com.br.desafio.backend.controller.exceptions

import java.io.Serializable

class StandardError : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    private var timestamp: Long = 0
    private var status: String = ""
    private var message: String = ""
    private var errorType: String = ""
    private var path: String = ""

    constructor(timestamp: Long, message: String, errorType: String, path: String, status: String) {
        this.timestamp = timestamp
        this.message = message
        this.errorType = errorType
        this.path = path
        this.status = status

    }

    constructor()

    override fun toString(): String {
        return "StandardError(timeStamp=$timestamp, status='$status', message='$message', errorType='$errorType', path='$path')"
    }


}