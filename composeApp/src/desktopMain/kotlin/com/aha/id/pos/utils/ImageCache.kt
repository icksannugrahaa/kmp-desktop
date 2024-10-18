package com.aha.id.pos.utils

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.Job

object ImageCache {
    private val cache = mutableMapOf<String, ImageBitmap>()
    private val inProgress = mutableMapOf<String, Job>()

    fun get(url: String): ImageBitmap? = cache[url]

    fun set(url: String, bitmap: ImageBitmap) {
        cache[url] = bitmap
    }

    fun isLoading(url: String): Boolean = url in inProgress

    fun setLoading(url: String, job: Job) {
        inProgress[url] = job
    }

    fun removeLoading(url: String) {
        inProgress.remove(url)
    }

}