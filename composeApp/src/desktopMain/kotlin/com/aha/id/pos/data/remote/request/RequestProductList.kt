package com.aha.id.pos.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestProductList(
    val page: Int? = null,
    val size: Int? = null,
    val token: String? = null
)
