package com.aha.id.pos.data.preference

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class AuthPreference(private val filePath: String) {
    private val properties = Properties()

    init {
        // Load existing properties from the file
        val file = File(filePath)
        if (file.exists()) {
            FileInputStream(file).use { input ->
                properties.load(input)
            }
        }
    }

    // Function to save a key-value pair
    fun putString(key: String, value: String) {
        properties.setProperty(key, value)
        saveProperties()
    }

    // Function to retrieve a value by key
    fun getString(key: String, defaultValue: String? = null): String? {
        return properties.getProperty(key, defaultValue)
    }

    // Function to save properties to a file
    private fun saveProperties() {
        FileOutputStream(filePath).use { output ->
            properties.store(output, null)
        }
    }

    // Function to clear the shared preferences
    fun clear() {
        properties.clear()
        saveProperties()
    }
}