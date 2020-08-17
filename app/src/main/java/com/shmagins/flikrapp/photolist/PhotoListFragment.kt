package com.shmagins.flikrapp.photolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.util.FixedPreloadSizeProvider
import com.shmagins.flikrapp.R

class PhotoListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_master, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.photo_list_recycler)
        recycler.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        recycler.adapter = PhotoListAdapter()
        return view
    }
}