package com.apx.Devocao

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Frases(
    val id: String,
    val titulo: String,
    val categoria: String,
    val autor: String,
    val linguagem: String
)

object FraseManager {
    private const val FRASES_PREF_KEY = "frases_preferences"

    fun saveFrases(context: Context, frases: List<Frases>) {
        val gson = Gson()
        val frasesJson = gson.toJson(frases)

        val sharedPreferences = context.getSharedPreferences(FRASES_PREF_KEY, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(FRASES_PREF_KEY, frasesJson)
        editor.apply()
    }

    fun getFrases(context: Context): List<Frases> {
        val sharedPreferences = context.getSharedPreferences(FRASES_PREF_KEY, Context.MODE_PRIVATE)
        val frasesJson = sharedPreferences.getString(FRASES_PREF_KEY, "")

        val gson = Gson()
        val type = object : TypeToken<List<Frases>>() {}.type

        return gson.fromJson(frasesJson, type) ?: emptyList()
    }
}
