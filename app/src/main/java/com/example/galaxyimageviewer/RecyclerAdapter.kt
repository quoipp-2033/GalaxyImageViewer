package com.example.galaxyimageviewer

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*
import android.content.Intent
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val photos: ArrayList<Photo>) : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>()  {
    class PhotoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var photo: Photo? = null
        //3
        init {
            v.setOnClickListener(this)
        }
        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context
            val showPhotoIntent = Intent(context, PhotoActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, photo)
            context.startActivity(showPhotoIntent)
        }

        fun bindPhoto(photo: Photo) {
            this.photo = photo
            Picasso.with(view.context).load(photo.url).into(view.itemImage)
            view.itemDate.text = photo.humanDate
            //view.itemDescription.text = photo.explanation
            //<TextView
            //        android:id="@+id/itemDescription"
            //        android:layout_width="wrap_content"
            //        android:layout_height="wrap_content"
            //        android:layout_gravity="center|start"
            //        android:layout_weight="1"
            //        android:ellipsize="end"
            //        android:maxLines="5" />
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return PhotoHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
        val itemPhoto = photos[position]
        holder.bindPhoto(itemPhoto)
    }

    override fun getItemCount() = photos.size

}
//1
