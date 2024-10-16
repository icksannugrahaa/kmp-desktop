package com.aha.id.pos.ui.theme

import androidx.compose.ui.graphics.Color
import com.aha.id.pos.data.enums.ButtonColors
import com.aha.id.pos.data.model.ButtonColor

object Colors {
    val white = Color(0xFFF5F5F5)
    val white100 = Color(0xFFF5F5F5)
    val white200 = Color(0xFFEDEDED)
    val white300 = Color(0xFFE0E0E0)
    val white400 = Color(0xFFF1F1F1)
    val grey = Color(0xFFC2C2C2)
    val grey100 = Color(0xFF9E9E9E)
    val grey200 = Color(0xFF757575)
    val grey300 = Color(0xFF616161)
    val black100 = Color(0xFF424242)
    val black200 = Color(0xFF0A0A0A)
    val orange = Color(0xFFFFA67E)
    val orange100 = Color(0xFFFF8A55)
    val orange200 = Color(0xFFF36B2D)
    val orange300 = Color(0xFFCF490C)
    val orange400 = Color(0xFFA43300)
    val yellow = Color(0xFFFFF8DD)
    val yellow100 = Color(0xFFFFC700)
    val yellow200 = Color(0xFFF1BC00)
    val yellow300 = Color(0xFFFFF5E7)
    val yellow400 = Color(0xFFFFA621)
    val yellow500 = Color(0xFFF69B11)
    val cyan = Color(0xFF52D4CB)
    val cyan100 = Color(0xFF2ACBBF)
    val cyan200 = Color(0xFF02C5B8)
    val cyan300 = Color(0xFF009A8F)
    val cyan400 = Color(0xFF00776E)
    val green = Color(0xFFE8FFF3)
    val green100 = Color(0xFF50CD89F)
    val green200 = Color(0xFF46BE7D)
    val green300 = Color(0xFFDFF1EB)
    val green400 = Color(0xFF4AB58E)
    val green500 = Color(0xFF2BA579)
    val red = Color(0xFFFEEEEF)
    val red100 = Color(0xFFEC1C24)
    val red200 = Color(0xFFD02D33)
    val blue = Color(0xFFECF8FF)
    val blue100 = Color(0xFF009EF6)
    val blue200 = Color(0xFF1A8BC9)
    val transparent = Color(0xFFFF000000)

    fun getButtonColor(color: ButtonColors, customBorderColor: Color? = null) : ButtonColor {
        return when(color) {
            ButtonColors.ORANGE -> {
                ButtonColor(
                    normal = orange200,
                    hover = orange300,
                    disabled = grey,
                    border = customBorderColor
                )
            }
            ButtonColors.BLUE -> {
                ButtonColor(
                    normal = blue100,
                    hover = blue200,
                    disabled = grey,
                    border = customBorderColor
                )
            }
            ButtonColors.RED -> {
                ButtonColor(
                    normal = red100,
                    hover = red200,
                    disabled = grey,
                    border = customBorderColor
                )
            }
            ButtonColors.GREEN -> {
                ButtonColor(
                    normal = green400,
                    hover = green500,
                    disabled = grey,
                    border = customBorderColor
                )
            }
            ButtonColors.BLACK -> {
                ButtonColor(
                    normal = black100,
                    hover = black200,
                    disabled = grey,
                    border = customBorderColor
                )
            }
            ButtonColors.WHITE -> {
                ButtonColor(
                    normal = Color.White,
                    hover = white100,
                    disabled = grey,
                    border = customBorderColor
                )
            }
        }
    }
}