package com.example.galaxyimageviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity(), ImageRequester.ImageRequesterResponse {

        private lateinit var linearLayoutManager: LinearLayoutManager
        private var photosList: ArrayList<Photo> = ArrayList()
        private lateinit var imageRequester: ImageRequester

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            linearLayoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = linearLayoutManager

            imageRequester = ImageRequester(this)
        }

        override fun onStart() {
            super.onStart()
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
            }
        }
    }