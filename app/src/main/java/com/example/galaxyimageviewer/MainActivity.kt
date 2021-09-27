package com.example.galaxyimageviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.ArrayList
import androidx.recyclerview.widget.StaggeredGridLayoutManager




class MainActivity : AppCompatActivity(), ImageRequester.ImageRequesterResponse {

    private lateinit var adapter: RecyclerAdapter
    private var photosList: ArrayList<Photo> = ArrayList()
    private lateinit var imageRequester: ImageRequester

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = RecyclerAdapter(photosList)
        recyclerView.adapter = adapter
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        imageRequester = ImageRequester(this)
    }

    override fun onStart() {
        super.onStart()
        if (photosList.size == 0) {
            requestPhoto()
        }
    }

    private fun requestPhoto() {
        try {
            imageRequester.getPhoto()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun receivedNewPhoto(newPhoto: Photo) {
        runOnUiThread {
            photosList.add(newPhoto)
            adapter.notifyItemInserted(photosList.size)
        }
    }
}