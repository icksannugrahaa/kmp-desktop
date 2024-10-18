package com.aha.id.pos.data.remote.response

import kotlinx.serialization.Serializable


@Serializable
data class ResponseProductList(
	val response_code: Int? = null,
	val code: String? = null,
	val data: List<ProductItem?>? = null,
	val link_download: String? = null,
	val total_data: Int? = null,
	val message: String? = null
)

