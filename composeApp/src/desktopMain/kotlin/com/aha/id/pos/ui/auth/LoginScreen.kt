package com.aha.id.pos.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.aha.id.pos.component.Button.customButton
import com.aha.id.pos.component.TextField.customTextField
import com.aha.id.pos.data.enums.ButtonColors
import com.aha.id.pos.data.enums.ButtonSize
import com.aha.id.pos.data.preference.AuthPreference
import com.aha.id.pos.data.remote.network.ApiService
import com.aha.id.pos.data.remote.request.RequestAuthLogin
import com.aha.id.pos.data.remote.response.ResponseAuthLogin
import com.aha.id.pos.ui.main.DashboardScreen
import com.aha.id.pos.ui.theme.Colors.black100
import com.aha.id.pos.ui.theme.Colors.grey100
import com.aha.id.pos.ui.theme.Colors.grey200
import com.aha.id.pos.ui.theme.Colors.orange200
import com.aha.id.pos.ui.theme.Colors.red100
import com.aha.id.pos.ui.theme.Colors.white
import com.aha.id.pos.ui.theme.Colors.white300
import com.aha.id.pos.utils.GlobalFunction.isValidEmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.vectorResource
import posahaid.composeapp.generated.resources.*
import posahaid.composeapp.generated.resources.Res
import posahaid.composeapp.generated.resources.ic_user
import posahaid.composeapp.generated.resources.roboto_bold
import posahaid.composeapp.generated.resources.roboto_regular
import java.awt.Cursor
import javax.swing.Icon
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class LoginScreen : Screen {

    private lateinit var navigator: Navigator
    private lateinit var authPreferences: AuthPreference

    @Composable
    override fun Content() {
        navigator = LocalNavigator.currentOrThrow
        authPreferences = AuthPreference("auth_prefs.properties")

        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isPinVisible by remember { mutableStateOf(false) }
        var errorMessageUsername by remember { mutableStateOf("") }
        var errorMessagePin by remember { mutableStateOf("") }
        var response by remember { mutableStateOf("wait for response ...") }
        var scope = rememberCoroutineScope()
        val token = authPreferences.getString("token", "")

        if(token == "")
        Column(
            modifier = Modifier.background(white).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                modifier = Modifier.width(381.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo
                Image(
                    painter = painterResource("image/logo.png"), // Replace with your actual logo resource
                    contentDescription = "Aha! Logo",
                    modifier = Modifier.size(75.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Welcome Text
                Text(
                    text = "Selamat Datang",
                    fontFamily = FontFamily(
                        Font(Res.font.roboto_bold, weight = FontWeight.W700)
                    ),
                    fontSize = 32.sp,
                    color = black100,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Instruction Text
                Text(
                    text = "Silahkan masukkan username dan password untuk mengakses POS AHA.id",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(
                        Font(Res.font.roboto_regular, weight = FontWeight.W400)
                    ),
                    fontSize = 16.sp,
                    color = black100
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Username Field
                customTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        errorMessageUsername = validation(it, true)
                    },
                    label = "Username",
                    placeholder = "Masukkan username",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(19.dp),
                            imageVector = vectorResource(Res.drawable.ic_user),
                            contentDescription = null
                        )
                    },
                    errorMessage = errorMessageUsername
                )

                Spacer(modifier = Modifier.height(20.dp))

                // PIN Field
                customTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        errorMessagePin = validation(it, false)
                    },
                    label = "Pin (6 Digit)",
                    placeholder = "123***",
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true,
                    pinVisibility = isPinVisible,
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(19.dp),
                            imageVector = vectorResource(Res.drawable.ic_lock),
                            contentDescription = null
                        )
                    },
                    withTrailingIcon = true,
                    errorMessage = errorMessagePin
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Sign In Button
                customButton(
                    size = ButtonSize.XL,
                    color = ButtonColors.ORANGE,
                    text = "Sign In",
                    maxWidth = true,
                    modifier = Modifier,
                    onClick = {
                        if(validation(username, true) == "" && validation(password, false) == "") {
                            scope.launch {
                                login(username, password)
                            }
                            println("RESP")
                        } else {
                            errorMessageUsername = validation(username, true)
                            errorMessagePin = validation(password, false)
                        }
                    }
                )

                Text(
                    response, fontSize = 16.sp, fontFamily = FontFamily(
                        Font(Res.font.roboto_regular, weight = FontWeight.W400)
                    ),
                    color = grey200
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Help Text
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Terdapat kendala? ", fontSize = 16.sp, fontFamily = FontFamily(
                            Font(Res.font.roboto_regular, weight = FontWeight.W400)
                        ),
                        color = grey200
                    )
                    Text(
                        "Hubungi admin", fontSize = 16.sp, fontFamily = FontFamily(
                            Font(Res.font.roboto_bold, weight = FontWeight.W600)
                        ),
                        color = orange200
                    )
                }
            }
        }
        else
            navigator.push(DashboardScreen(token.toString()))
    }

    private suspend fun login(username: String, password: String) {
        val response = ApiService().login(RequestAuthLogin(username, password))
        val user = Json.decodeFromString<ResponseAuthLogin>(response)
        authPreferences.putString("name", user.name.toString())
        authPreferences.putString("email", user.email.toString())
        authPreferences.putString("token", user.token.toString())
        authPreferences.putString("role", user.role_name.toString())
        navigator.push(DashboardScreen(user.token.toString()))
        println("SAVED")
    }

    private fun validation(text: String, event: Boolean): String {
        return when {
            text == "" && event -> {
                "Username tidak boleh kosong"
            }

            text == "" && !event -> {
                "Pin tidak boleh kosong"
            }

//            text.length < 6 && !event -> {
//                "Pin tidak boleh kurang dari 6 digit"
//            }

            else -> {
               ""
            }
        }
    }
}