package com.aha.id.pos.data.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.aha.id.pos.data.enums.ButtonSize

data class ButtonConfiguration(
    val width: Dp? = null,
    val height: Dp,
    val corner: Dp,
    val buttonColor: ButtonColor,
    val textColor: Color? = Color.White,
    val textStyle: TextStyle,
    val text: String,
    val onSelectedColor: ButtonColor? = null,
    val icon: ImageVector? = null,
    val iconAtStart: Boolean = true,
    val isOutlined: Boolean = false,
    val isEnabled: Boolean = true,
    val modifier: Modifier,
    val modifierChild: Modifier?
)
