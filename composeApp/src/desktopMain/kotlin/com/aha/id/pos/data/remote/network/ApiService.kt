package com.aha.id.pos.data.remote.network

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.aha.id.pos.data.remote.request.RequestAuthLogin
import com.aha.id.pos.data.remote.request.RequestProductList
import com.aha.id.pos.data.remote.response.ResponseAuthLogin
import com.aha.id.pos.utils.Constants.BASE_URL
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.auth.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.net.URL
import kotlin.text.get


class ApiService {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 60_000
        }
        //Logging plugin combined with kermit(KMP Logger library)
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object: Logger {
                override fun log(message: String) {
//                    co.touchlab.kermit.Logger.d(tag = "KtorClient") {
//                        message
//                    }
                }
            }
        }
        CIO
    }

    suspend fun loadImageBitmap(url: String): ImageBitmap = withContext(Dispatchers.IO) {
        val response: HttpResponse = client.get(url)
        val bytes = response.readBytes()
        val inputStream: InputStream = ByteArrayInputStream(bytes)
        org.jetbrains.skia.Image.makeFromEncoded(inputStream.readBytes()).toComposeImageBitmap()
    }

    suspend fun login(request: RequestAuthLogin) : String {
        val resp = client.post {
            url("$BASE_URL/cms_v2/sessions/login")
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return when {
            resp.status.isSuccess() -> {
                resp.bodyAsText()
            }
            else -> {
                resp.status.description
            }
        }
    }

    suspend fun listProduct(request: RequestProductList) : String {
        val resp = client.get {
            url("https://ahaid-api-cms-dev.up.railway.app/cms/master_data/list_master_product?page=${request.page}&limit=${request.size}&search=&sort=${"product_name asc".encodeURLParameter()}&principle_ids=&validate_parent=")
            headers {
                append(HttpHeaders.Authorization, "Bearer ${request.token}")
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }
        }
        return when {
            resp.status.isSuccess() -> {
                resp.bodyAsText()
            }
            else -> {
                resp.toString()
            }
        }
    }
}