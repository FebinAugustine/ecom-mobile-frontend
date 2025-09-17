package com.ecom.core.common

sealed class Failure(val message: String) {
    class NetworkFailure(message: String) : Failure(message)
    class ServerFailure(message: String) : Failure(message)
    class UnknownFailure(message: String = "An unknown error occurred") : Failure(message)

    companion object {
        fun fromException(e: Exception): Failure {
            return when (e) {
                // Add specific exception mappings here, e.g., for Ktor's exceptions
                else -> UnknownFailure(e.message ?: "An unknown error occurred")
            }
        }
    }
}
