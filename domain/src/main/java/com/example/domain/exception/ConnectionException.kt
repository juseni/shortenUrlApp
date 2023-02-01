package com.example.domain.exception

data class ConnectionException(
    override val message: String = "Connectivity issue"
) : Exception()
