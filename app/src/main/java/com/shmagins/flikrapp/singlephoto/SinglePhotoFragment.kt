package com.shmagins.flikrapp.singlephoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.shmagins.flikrapp.R

class SinglePhotoFragment(val url: String, val title: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        if (url == "") {
            view.findViewById<TextView>(R.id.photo_placeholder).visibility = View.VISIBLE
        } else {
            Glide
                .with(requireActivity())
                .load(url)
                .into(view.findViewById(R.id.image))
            view.findViewById<TextView>(R.id.title).text = title
        }
        return view
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(URL, url)
        outState.putString(TITLE, title)
    }

    companion object {
        const val URL: String = "URL"
        const val TITLE: String = "TITLE"
    }
}