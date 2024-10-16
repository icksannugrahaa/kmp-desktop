package com.aha.id.pos.data.model

data class ProductItem(
    val id: Int,
    val name: String,
    val sku: String,
    val principal: String,
    val treeUnit: String,
    val cnv: String,
    val image: String // Drawable resource ID

)
