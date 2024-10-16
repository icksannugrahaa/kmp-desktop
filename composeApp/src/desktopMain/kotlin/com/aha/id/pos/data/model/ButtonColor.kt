package com.aha.id.pos.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.aha.id.pos.data.enums.ButtonSize

data class ButtonColor(
    val normal: Color,
    val disabled: Color,
    val hover: Color,
    val border: Color? = null
)
