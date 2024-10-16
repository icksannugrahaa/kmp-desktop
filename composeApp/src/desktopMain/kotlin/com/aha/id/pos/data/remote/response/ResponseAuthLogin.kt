package com.aha.id.pos.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseAuthLogin(
    val id: Int? = null,
    val slug: String? = null,
    val user_name: String? = null,
    val role_id: String? = null,
    val role_name: String? = null,
    val email: String? = null,
    val name: String? = null,
    val distributor_id: String? = null,
    val token: String? = null
)
