package com.example.android4a.data.local.models

import com.example.android4a.presentation.main.RestMusicResponse
import retrofit2.Call
import retrofit2.http.GET

interface MusicAPI {
    @GET("/api/v1/json/1/discography.php?s=coldplay")
    fun getMusicResponse() : Call<RestMusicResponse>
}