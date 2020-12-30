package com.example.android4a.presentation.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android4a.R
import com.example.android4a.Singletons

class DetailActivity : AppCompatActivity() {
    private var txtDetail: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        txtDetail = findViewById(R.id.detail_txt)
        val intent = intent
        val musicJson = intent.getStringExtra("musicKeyAlbum")
        val music: Music = Singletons.gson!!.fromJson(musicJson, Music::class.java)
        showDetail(music)
    }

    private fun showDetail(music: Music) {
        txtDetail!!.text = music.strAlbum
    }
}