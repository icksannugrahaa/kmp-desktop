package com.aha.id.pos.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aha.id.pos.ui.theme.Colors.black100
import com.aha.id.pos.ui.theme.Colors.black200
import com.aha.id.pos.ui.theme.Colors.grey
import com.aha.id.pos.ui.theme.Colors.grey100
import com.aha.id.pos.ui.theme.Colors.red100
import com.aha.id.pos.ui.theme.Colors.white300
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import posahaid.composeapp.generated.resources.*
import posahaid.composeapp.generated.resources.Res
import posahaid.composeapp.generated.resources.ic_eye_close
import posahaid.composeapp.generated.resources.ic_eye_open
import posahaid.composeapp.generated.resources.roboto_regular
import java.awt.Cursor

object TextField {

    fun bottomRoundedCornerShape(cornerRadius: Int) = GenericShape { size, layoutDirection ->
        val cornerRadiusPx = cornerRadius.toFloat()

        moveTo(0f, 0f)  // Top-left corner
        lineTo(size.width, 0f)  // Top side
        lineTo(size.width, size.height - cornerRadiusPx)  // Right side
        quadraticBezierTo(
            size.width, size.height,
            size.width - cornerRadiusPx, size.height
        )  // Bottom-right curve
        lineTo(cornerRadiusPx, size.height)  // Bottom side
        quadraticBezierTo(
            0f, size.height,
            0f, size.height - cornerRadiusPx
        )  // Bottom-left curve
        close()
    }


    fun topRoundedCornerShape(cornerRadius: Int) = GenericShape { size, layoutDirection ->
        val cornerRadiusPx = cornerRadius.toFloat()

        moveTo(cornerRadiusPx, 0f)  // Start from top-left after the curve
        quadraticBezierTo(
            0f, 0f,
            0f, cornerRadiusPx
        )  // Top-left curve
        lineTo(0f, size.height)  // Left side
        lineTo(size.width, size.height)  // Bottom side
        lineTo(size.width, cornerRadiusPx)  // Right side
        quadraticBezierTo(
            size.width, 0f,
            size.width - cornerRadiusPx, 0f
        )  // Top-right curve
        close()
    }

    @Composable
    fun customTextField(
        value: String,
        onValueChange: (String) -> Unit,
        placeholder: String,
        modifier: Modifier = Modifier,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        withTrailingIcon: Boolean = false,
        isPassword: Boolean = false,
        pinVisibility: Boolean = false,
        singleLine: Boolean = true,
        label: String? = null,
        errorMessage: String? = null,
        isDigitOnly: Boolean = false,
        maxLength: Int? = 0
    ) {
        var isHovered by remember { mutableStateOf(false) }
        var isPinVisible by remember { mutableStateOf(pinVisibility) }
        Column(modifier = modifier) {
            if (label != null)
                Text(
                    text = label,
                    color = black100,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(Res.font.roboto_regular, weight = FontWeight.W400)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    print(isPassword)
                    if(maxLength == 0 || (maxLength ?: 0) > 0 && newValue.length <= (maxLength ?: 0)) {
                        if (isDigitOnly) {
                            if (newValue.all { it.isDigit() }) {
                                onValueChange(newValue)
                            }
                        } else {
                            onValueChange(newValue)
                        }
                    }
                },
                placeholder = {
                    Text(
                        placeholder, color = grey100,
                        fontFamily = FontFamily(
                            Font(Res.font.roboto_regular, weight = FontWeight.W400)
                        ),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = black100,
                    unfocusedIndicatorColor = if (isHovered) grey else white300,
                    disabledIndicatorColor = white300,
                    errorIndicatorColor = red100,
                    textColor = black200,
                    disabledTextColor = black100,
                    placeholderColor = grey100
                ),
                isError = (errorMessage != ""),
                textStyle = TextStyle(
                    color = black100,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(
                        Font(Res.font.roboto_regular, weight = FontWeight.W400)
                    )
                ),
                shape = if (errorMessage != "") topRoundedCornerShape(8) else RoundedCornerShape(8.dp),
                leadingIcon = leadingIcon,
                singleLine = singleLine,
                trailingIcon = if (withTrailingIcon) {
                    {
                        if(isPassword) {
                            IconButton(
                                onClick = { isPinVisible = !isPinVisible }, modifier = Modifier.pointerHoverIcon(
                                    PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                                )
                            ) {
                                Icon(
                                    imageVector = if (isPinVisible) vectorResource(Res.drawable.ic_eye_open) else vectorResource(
                                        Res.drawable.ic_eye_close
                                    ),
                                    contentDescription = if (isPinVisible) "Hide PIN" else "Show PIN"
                                )
                            }
                        } else {
                            trailingIcon!!.invoke()
                        }
                    }
                } else null,
                visualTransformation = if (isPassword) if (isPinVisible) VisualTransformation.None else PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.NumberPassword) else KeyboardOptions.Default,
                modifier = Modifier.fillMaxWidth()
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent()
                                when (event.type) {
                                    PointerEventType.Enter -> isHovered = true
                                    PointerEventType.Exit -> isHovered = false
                                    else -> {}
                                }
                            }
                        }
                    }

            )
            if (errorMessage != "")
                Row(
                    modifier = modifier
                        .clip(bottomRoundedCornerShape(8))
                        .background(red100),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = vectorResource(Res.drawable.ic_error_triangle),
                        contentDescription = "Field Error",
                        modifier = Modifier
                            .padding(start = 12.dp, end = 8.dp, bottom = 4.dp, top = 4.dp)
                    )
                    Text(
                        text = errorMessage.toString(),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(Res.font.roboto_regular, weight = FontWeight.W400)
                        ),
                        modifier = Modifier
                            .padding(end = 12.dp, bottom = 6.dp, top = 2.dp)
                    )
                }
        }
    }
}