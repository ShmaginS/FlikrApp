package com.shmagins.flikrapp.photolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shmagins.flikrapp.R

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.PhotoListRecordViewHolder>() {

    class PhotoListRecordViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.panda_image)

        public fun bind(imagePath: String) {

        }
    }

    public fun setPhotos(photos: List<String>){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListAdapter.PhotoListRecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.panda_photo_card, parent, false)
        return PhotoListRecordViewHolder(view)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoListAdapter.PhotoListRecordViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    private var photos: List<String> = ArrayList()
}