package com.example.android4a.presentation.main

import android.content.SharedPreferences
import com.example.android4a.Constants
import com.example.android4a.Singletons
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainController(
    private val view: ListActivity,
    private val gson: Gson,
    private val sharedPreferences: SharedPreferences
) {
    fun onStart() {
        var musicList = dataFromCache
        if (musicList != null) {
            view.showList(musicList)
        } else {
            makeAPICall()
        }
    }

    private fun makeAPICall() {
        val call: Call<RestMusicResponse> = Singletons.getMusicAPI()!!.getMusicResponse()
        call.enqueue(object : Callback<RestMusicResponse?> {
            override fun onResponse(
                call: Call<RestMusicResponse?>,
                response: Response<RestMusicResponse?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    var musicList = response.body()!!.getAlbum()
                    saveList(musicList)
                    //Toast.makeText(MainActivity.this, "API success", Toast.LENGTH_SHORT).show();
                    if (musicList != null) {
                        view.showList(musicList)
                    }
                } else {
                    view.showError()
                }
            }

            override fun onFailure(
                call: Call<RestMusicResponse?>,
                t: Throwable
            ) {
                view.showError()
            }
        })
    }

    private fun saveList(musicList: List<Music>?) {
        val jsonString = gson.toJson(musicList)
        sharedPreferences
            .edit()
            .putString(Constants.KEY_MUSIC_LIST, jsonString)
            .apply()
        //Toast.makeText(this, "List saved", Toast.LENGTH_SHORT).show();
    }

    private val dataFromCache: List<Music>?
        private get() {
            val jsonMusic = sharedPreferences.getString(Constants.KEY_MUSIC_LIST, null)
            return if (jsonMusic == null) {
                null
            } else {
                val listType =
                    object : TypeToken<List<Music?>?>() {}.type
                gson.fromJson<List<Music>>(jsonMusic, listType)
            }
        }

    fun onItemClick(music: Music?) {
        if (music != null) {
            view.navigateToDetails(music)
        }
    }

    fun onButtonAClick() {}
    fun onButtonBClick() {}

}