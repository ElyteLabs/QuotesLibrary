package com.elytelabs.quoteutils.utils

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.elytelabs.quoteutils.R

object PrefUtils {

    private const val SHARED_PREFS_NAME = "app_prefs"
    private const val BACKGROUND_IMAGE_KEY = "background_image"
    private const val BACKGROUND_COLOR_KEY = "background_color"
    private const val TYPEFACE_KEY = "typeface"
    private lateinit var preferences: SharedPreferences

    // Get Typeface
    fun getTypefaceFromPrefs(context: Context): Typeface {
        preferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val font = preferences.getInt(TYPEFACE_KEY, R.font.jose)
        return ResourcesCompat.getFont(context, font)!!
    }

    // Set Typeface
    fun saveTypefaceToPrefs(context: Context, font: Int) {
        preferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(TYPEFACE_KEY, font)
        editor.apply()
    }

    fun getBackgroundImage(context: Context): Int {
        preferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
       return preferences.getInt(BACKGROUND_IMAGE_KEY, 0)
    }

     fun getBackgroundColor(context: Context): Int {
        preferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        return preferences.getInt(BACKGROUND_COLOR_KEY, 0)
    }

    fun setBackgroundImage(context: Context, backgroundImage: Int) {
        preferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(BACKGROUND_IMAGE_KEY, backgroundImage)
        editor.remove(BACKGROUND_COLOR_KEY)
        editor.apply()
    }

    fun setBackgroundColor(context: Context, backgroundColor: Int) {
        preferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(BACKGROUND_COLOR_KEY, backgroundColor)
        editor.remove(BACKGROUND_IMAGE_KEY)
        editor.apply()
    }
}