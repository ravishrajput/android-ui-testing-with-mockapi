package com.ravish.android.uitesting.withmockapi.commons.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonLoader {

    inline fun <reified U> objectFromJsonFileWithType(fileNameWithPath: String): U {
        val type = object : TypeToken<U>() {}.type
        return Gson().fromJson(fileAsString(fileNameWithPath), type)
    }

    fun fileAsString(fileNameWithPath: String): String = this::class.java
        .getResourceAsStream(fileNameWithPath)!!
        .bufferedReader()
        .use { it.readText() }
}