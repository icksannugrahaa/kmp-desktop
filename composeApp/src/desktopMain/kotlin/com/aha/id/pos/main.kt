package com.aha.id.pos

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.ExperimentalResourceApi
import androidx.compose.material.Typography
import org.jetbrains.compose.resources.Font
import posahaid.composeapp.generated.resources.*
import posahaid.composeapp.generated.resources.Res
import posahaid.composeapp.generated.resources.roboto_bold
import posahaid.composeapp.generated.resources.roboto_light
import posahaid.composeapp.generated.resources.roboto_regular

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "POS AHA ID",
    ) {
        App()
    }
}