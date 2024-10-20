package com.simple.di.storageService

import android.content.SharedPreferences

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

import javax.inject.Inject

val KEY_ = "key-01"

class StorageService @Inject constructor(
     val app: SharedPreferences,
) {
    fun store( value: List<String>) {
        app.edit().putString(KEY_,  Gson().toJson(value)).apply()
    }
    fun get(): List<String> {
        val type: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(app.getString(KEY_, "[]"), type)
    }
}