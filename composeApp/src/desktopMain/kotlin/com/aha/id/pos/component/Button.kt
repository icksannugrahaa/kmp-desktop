package com.aha.id.pos.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aha.id.pos.data.enums.ButtonColors
import com.aha.id.pos.data.enums.ButtonSize
import com.aha.id.pos.data.model.ButtonColor
import com.aha.id.pos.data.model.ButtonConfiguration
import com.aha.id.pos.ui.theme.Colors.getButtonColor
import com.aha.id.pos.ui.theme.Colors.grey
import org.jetbrains.compose.resources.Font
import posahaid.composeapp.generated.resources.Res
import posahaid.composeapp.generated.resources.roboto_bold
import java.awt.Cursor

object Button {
    @Composable
    fun customButton(
        size: ButtonSize,
        color: ButtonColors,
        text: String,
        textColor: Color? = null,
        maxWidth: Boolean = false,
        isOutline: Boolean = false,
        icon: ImageVector? = null,
        iconPositionStart: Boolean = false,
        onClick: () -> Unit? = {},
        modifier: Modifier,
        customBorderColor: Color? = null,
        onSelectedColor: ButtonColor? = null,
        modifierChild: Modifier? = null
    ) {
        when (size) {
            ButtonSize.XL -> {
                val configuration = ButtonConfiguration(
                    height = 51.dp,
                    buttonColor = getButtonColor(color, customBorderColor),
                    corner = 8.dp,
                    icon = icon,
                    textStyle = TextStyle(
                        color = textColor ?: Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(Res.font.roboto_bold, weight = FontWeight.W600)
                        )
                    ),
                    text = text,
                    isEnabled = true,
                    isOutlined = isOutline,
                    modifier = modifier,
                    iconAtStart = iconPositionStart,
                    textColor = textColor,
                    onSelectedColor = onSelectedColor,
                    modifierChild = modifierChild
                )
                if (isOutline)
                    outlinedButton(
                        configuration = configuration,
                        maxWidth = maxWidth,
                        onClick = onClick
                    ) else button(
                    configuration = configuration,
                    maxWidth = maxWidth,
                    onClick = onClick
                )
            }

            ButtonSize.L -> {
                val configuration = ButtonConfiguration(
                    height = 43.dp,
                    buttonColor = getButtonColor(color, customBorderColor),
                    corner = 8.dp,
                    icon = icon,
                    textStyle = TextStyle(
                        color = textColor ?: Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(Res.font.roboto_bold, weight = FontWeight.W600)
                        )
                    ),
                    text = text,
                    isEnabled = true,
                    isOutlined = isOutline,
                    modifier = modifier,
                    iconAtStart = iconPositionStart,
                    textColor = textColor,
                    onSelectedColor = onSelectedColor,
                    modifierChild = modifierChild
                )
                if (isOutline)
                    outlinedButton(
                        configuration = configuration,
                        maxWidth = maxWidth,
                        onClick = onClick
                    ) else button(
                    configuration = configuration,
                    maxWidth = maxWidth,
                    onClick = onClick
                )
            }

            ButtonSize.M -> {
                val configuration = ButtonConfiguration(
                    height = 40.dp,
                    buttonColor = getButtonColor(color, customBorderColor),
                    corner = 8.dp,
                    icon = icon,
                    textStyle = TextStyle(
                        color = textColor ?: Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(Res.font.roboto_bold, weight = FontWeight.W600)
                        )
                    ),
                    text = text,
                    isEnabled = true,
                    isOutlined = isOutline,
                    modifier = modifier,
                    iconAtStart = iconPositionStart,
                    textColor = textColor,
                    onSelectedColor = onSelectedColor,
                    modifierChild = modifierChild
                )
                if (isOutline)
                    outlinedButton(
                        configuration = configuration,
                        maxWidth = maxWidth,
                        onClick = onClick
                    ) else button(
                    configuration = configuration,
                    maxWidth = maxWidth,
                    onClick = onClick
                )
            }

            ButtonSize.S -> {
                val configuration = ButtonConfiguration(
                    height = 32.dp,
                    buttonColor = getButtonColor(color, customBorderColor),
                    corner = 8.dp,
                    icon = icon,
                    textStyle = TextStyle(
                        color = textColor ?: Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(Res.font.roboto_bold, weight = FontWeight.W600)
                        )
                    ),
                    text = text,
                    isEnabled = true,
                    isOutlined = isOutline,
                    modifier = modifier,
                    iconAtStart = iconPositionStart,
                    textColor = textColor,
                    onSelectedColor = onSelectedColor,
                    modifierChild = modifierChild
                )
                if (isOutline)
                    outlinedButton(
                        configuration = configuration,
                        maxWidth = maxWidth,
                        onClick = onClick
                    ) else button(
                    configuration = configuration,
                    maxWidth = maxWidth,
                    onClick = onClick
                )
            }

            ButtonSize.XS -> {
                val configuration = ButtonConfiguration(
                    height = 28.dp,
                    buttonColor = getButtonColor(color, customBorderColor),
                    corner = 8.dp,
                    icon = icon,
                    textStyle = TextStyle(
                        color = textColor ?: Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(Res.font.roboto_bold, weight = FontWeight.W600)
                        )
                    ),
                    text = text,
                    isEnabled = true,
                    isOutlined = isOutline,
                    modifier = modifier,
                    iconAtStart = iconPositionStart,
                    textColor = textColor,
                    onSelectedColor = onSelectedColor,
                    modifierChild = modifierChild
                )
                if (isOutline)
                    outlinedButton(
                        configuration = configuration,
                        maxWidth = maxWidth,
                        onClick = onClick
                    ) else button(
                    configuration = configuration,
                    maxWidth = maxWidth,
                    onClick = onClick
                )
            }
        }
    }

    @Composable
    fun button(configuration: ButtonConfiguration, maxWidth: Boolean = true, onClick: () -> Unit? = {}) {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        with(configuration) {
            val textColor = when {
                !configuration.isEnabled -> Color.White
                isHovered -> if (textColor != null) textColor else Color.White
                else -> if (textColor != null) textColor else Color.White
            }

            val backgroundColor = when {
                !configuration.isEnabled -> buttonColor.disabled
                isHovered -> buttonColor.hover
                else -> buttonColor.normal
            }

            Button(
                onClick = { onClick() },
                modifier = configuration.modifier.height(configuration.height)
                    .let {
                        if (!maxWidth) it.wrapContentWidth() else it.fillMaxWidth()
                    }
                    .pointerHoverIcon(
                        PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                    ),
                shape = RoundedCornerShape(configuration.corner),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = backgroundColor,
                    contentColor = textColor,
                    disabledContentColor = buttonColor.disabled
                ),
                enabled = isEnabled,
                interactionSource = interactionSource, contentPadding = PaddingValues(0.dp)

            ) {
                Row(modifier = configuration.modifierChild ?: Modifier) {
                    if (icon != null && iconAtStart) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = textColor
                        )
                        Spacer(Modifier.width(6.dp))
                    }
                    if (text != "")
                        Text(
                            text = text,
                            textAlign = TextAlign.Center,
                            color = textColor ?: textStyle.color,
                            fontFamily = textStyle.fontFamily,
                            fontSize = textStyle.fontSize
                        )
                    if (icon != null && !iconAtStart) {
                        Spacer(Modifier.width(6.dp))
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = textColor
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun outlinedButton(configuration: ButtonConfiguration, maxWidth: Boolean = true, onClick: () -> Unit? = {}) {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        with(configuration) {
            val textColorUsed = when {
                !configuration.isEnabled -> buttonColor.disabled
                isHovered -> if (textColor != null) textColor else Color.White
                else -> if (textColor != null) textColor else buttonColor.normal
            }

            val backgroundColor = when {
                !configuration.isEnabled -> Color.White
                isHovered -> if(onSelectedColor != null) onSelectedColor.hover else buttonColor.normal
                else -> if(onSelectedColor != null) onSelectedColor.normal else Color.White
            }

            val borderColorUsed = when {
                !configuration.isEnabled -> buttonColor.disabled
                isHovered -> if(onSelectedColor != null) onSelectedColor.hover else if (textColor != null) textColor else buttonColor.normal
                else -> if(onSelectedColor != null) onSelectedColor.border else if (buttonColor.border != null) buttonColor.border else buttonColor.hover
            }

            OutlinedButton(
                onClick = { onClick() },
                modifier = configuration.modifier.height(configuration.height)
                    .let {
                        if (!maxWidth) it.wrapContentWidth() else it.fillMaxWidth()
                    }
                    .pointerHoverIcon(
                        PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                    ),
                shape = RoundedCornerShape(configuration.corner),
                border = BorderStroke(1.dp, borderColorUsed!!),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = backgroundColor,
                    contentColor = textColorUsed,
                    disabledContentColor = buttonColor.disabled
                ),
                enabled = isEnabled,
                interactionSource = interactionSource, contentPadding = PaddingValues(0.dp)

            ) {
                Row(modifier = configuration.modifierChild ?: Modifier) {
                    println("${icon != null} - $iconAtStart")
                    if (icon != null && iconAtStart) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = textColorUsed
                        )
                        Spacer(Modifier.width(6.dp))
                    }
                    if (text != "")
                        Text(
                            text = text,
                            textAlign = TextAlign.Center,
                            color = textColor ?: textStyle.color,
                            fontFamily = textStyle.fontFamily,
                            fontSize = textStyle.fontSize
                        )
                    if (icon != null && !iconAtStart) {
                        Spacer(Modifier.width(6.dp))
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = textColorUsed
                        )
                    }
                }
            }
        }
    }
}