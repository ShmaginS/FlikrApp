package com.shmagins.flikrapp.photolist

import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shmagins.flikrapp.R
import com.shmagins.flikrapp.common.MainActivityViewModel
import com.shmagins.flikrapp.common.ReadyPhoto
import com.shmagins.flikrapp.main.MainActivity
import com.shmagins.flikrapp.singlephoto.SinglePhotoActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable

class PhotoListAdapter() : RecyclerView.Adapter<PhotoListAdapter.PhotoListRecordViewHolder>() {
    class PhotoListRecordViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.image)
        val container = view.findViewById<ConstraintLayout>(R.id.photo_card_container)

        init {
            val windowManager = view.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            windowManager.defaultDisplay.getSize(point)
            container.layoutParams.width = point.x / 2 - 10
        }

        public fun bind(readyPhoto: ReadyPhoto) {
            Glide.with(imageView.context)
                .load(readyPhoto.url)
                .into(imageView)
            imageView.setOnClickListener {
                val activityContext: Context = view.context
                if (activityContext is MainActivity) {
                    activityContext.openDetail(readyPhoto.url, readyPhoto.title)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListRecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_card, parent, false)
        return PhotoListRecordViewHolder(view)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoListRecordViewHolder, position: Int) {
        holder.bind(photos[position])
    }



    fun addPhoto(readyPhoto: ReadyPhoto) {
        photos.add(readyPhoto)
        notifyItemInserted(photos.size-1)
    }

    fun clearPhotos() {
        photos.clear()
        notifyDataSetChanged()
    }

    @Volatile private var photos: MutableList<ReadyPhoto> = ArrayList()
}