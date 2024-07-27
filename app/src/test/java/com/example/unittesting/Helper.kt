package com.example.unittesting

import java.io.InputStreamReader


object Helper {

    fun readFileResource(filename: String) : String {
        val inputStream = Helper::class.java.getResourceAsStream("/response.json")
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}