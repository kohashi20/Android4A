package com.example.android4a

import android.content.Context
import android.content.SharedPreferences
import com.example.android4a.Constants.BASE_URL
import com.example.android4a.data.local.models.MusicAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Singletons {
    private var gsonInstance: Gson? = null
    private var musicAPIInstance: MusicAPI? = null
    private var sharedPreferencesInstance: SharedPreferences? = null
    val gson: Gson?
        get() {
            if (gsonInstance == null) {
                gsonInstance = GsonBuilder()
                    .setLenient()
                    .create()
            }
            return gsonInstance
        }

    fun getMusicAPI(): MusicAPI? {
            if (musicAPIInstance == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                musicAPIInstance = retrofit.create<MusicAPI>(MusicAPI::class.java)
            }
            return musicAPIInstance
        }

    fun getSharedPreferences(context: Context): SharedPreferences? {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences(
                "Music_Application",
                Context.MODE_PRIVATE
            )
        }
        return sharedPreferencesInstance
    }
}