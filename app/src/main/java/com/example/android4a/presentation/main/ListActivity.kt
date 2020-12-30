package com.example.android4a.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.R
import com.example.android4a.Singletons

class ListActivity : AppCompatActivity(){
private var recyclerView: RecyclerView,
private var mAdapter:ListAdapter,
private var layoutManager:RecyclerView.LayoutManager,
private var controller:MainController
protected fun onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.music_list)
    controller = MainController(
        this,
        Singletons.gson,
        Singletons.getSharedPreferences(getApplicationContext())
    )
    controller.onStart()
    //showList();
}
fun showList(musicList:List<Music>) {
    recyclerView = findViewById(R.id.Recycle_View) as RecyclerView
    // use this setting to
    // improve performance if you know that changes
    // in content do not change the layout size
    // of the RecyclerView
    recyclerView.setHasFixedSize(true)
    // use a linear layout manager
    layoutManager = LinearLayoutManager(this)
    recyclerView.setLayoutManager(layoutManager)
    recyclerView.setOnClickListener(object:View.OnClickListener() {
        fun onClick(v: View) {
        }
    })
    mAdapter = ListAdapter(musicList, object:ListAdapter.OnItemClickListener() {
        fun onItemClick(item:Music) {
            controller.onItemClick(item)
        }
    })
    recyclerView.setAdapter(mAdapter)
}
fun showError() {
    Toast.makeText(this, "API error", Toast.LENGTH_SHORT).show()
}
fun navigateToDetails(music:Music) {
    val myIntent = Intent(this@ListActivity, DetailActivity::class.java)
    myIntent.putExtra("musicKeyAlbum", Singletons.gson!!.toJson(music))
    //myIntent.putExtra("musickeyYear", music.getIntYearReleased());
    this@ListActivity.startActivity(myIntent)
}}