package com.aha.id.pos.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ProductItem(
    val note: String? = null,
    val rb_unit: String? = null,
    val gcat_name: String? = null,
    val sku_internal: String? = null,
    val CreatedAt: String? = null,
    val vpva_name: String? = null,
    val description: String? = null,
    val UpdatedAt: String? = null,
    val updated_at: String? = null,
    val vpva_min_qty: Int? = null,
    val ID: Int? = null,
    val DeletedAt: String? = null,
    val is_bkp: String? = null,
    val vpva_min_unit: String? = null,
    val height: Int? = null,
    val vpva_description: String? = null,
    val vpva_id: Int? = null,
    val image: String? = null,
    val tree_units: List<String?>? = null,
    val update_string: String? = null,
    val length: Int? = null,
    val weight: Int? = null,
    val cnv: List<String?>? = null,
    val product_name: String? = null,
    val gpro_id: Int? = null,
    val gpro_name: String? = null,
    val volume: Int? = null,
    val unit: String? = null,
    val qty_rb: Int? = null,
    val updated_by: String? = null,
    val width: Int? = null,
    val vpva_ids: List<Int?>? = null,
    val gpri_company_name: String? = null,
    val sku_barcode: String? = null,
    val sku_external: String? = null
)
