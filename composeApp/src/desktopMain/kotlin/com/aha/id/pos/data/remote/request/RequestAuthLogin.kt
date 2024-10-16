package com.aha.id.pos.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestAuthLogin(
    val login: String? = null,
    val password: String? = null
)
