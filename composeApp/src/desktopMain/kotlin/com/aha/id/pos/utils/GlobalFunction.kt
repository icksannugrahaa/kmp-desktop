package com.aha.id.pos.utils

object GlobalFunction {
    fun isValidEmail(email: String): Boolean {
        // Implement your email validation logic here
        return email.contains("@") && email.contains(".")
    }
}