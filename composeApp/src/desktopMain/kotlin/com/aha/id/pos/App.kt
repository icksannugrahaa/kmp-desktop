package com.aha.id.pos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.aha.id.pos.ui.auth.LoginScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import posahaid.composeapp.generated.resources.*
import posahaid.composeapp.generated.resources.Res
import posahaid.composeapp.generated.resources.roboto_light
import posahaid.composeapp.generated.resources.roboto_regular

@Composable
@Preview
fun App() {
    MaterialTheme(typography = RobotoTypography()) {
        Navigator(LoginScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RobotoFontFamily() = FontFamily(
    Font(Res.font.roboto_light, weight = FontWeight.Light),
    Font(Res.font.roboto_regular, weight = FontWeight.Normal),
    Font(Res.font.roboto_medium, weight = FontWeight.Medium),
    Font(Res.font.roboto_bold, weight = FontWeight.Bold)
)

@Composable
fun RobotoTypography() = Typography().run {
    val fontFamily = RobotoFontFamily()
    copy(
        h1 = h1.copy(fontFamily = fontFamily),
        h2 = h2.copy(fontFamily = fontFamily),
        h3 = h3.copy(fontFamily = fontFamily),
        h4 = h4.copy(fontFamily = fontFamily),
        h5 = h5.copy(fontFamily = fontFamily),
        h6 = h6.copy(fontFamily = fontFamily),
        subtitle1 = subtitle1.copy(fontFamily = fontFamily),
        subtitle2 = subtitle2.copy(fontFamily = fontFamily),
        body1 = body1.copy(fontFamily = fontFamily),
        body2 = body2.copy(fontFamily = fontFamily),
        button = button.copy(fontFamily = fontFamily)
    )
}