package com.shmagins.flikrapp.singlephoto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.shmagins.flikrapp.R

class SinglePhotoFragment(var url: String, var title: String) : Fragment() {
    constructor() : this("", "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail, container, false)


    override fun onStart() {
        super.onStart()
        view?.let {
            if (url == "") {
                it.findViewById<TextView>(R.id.photo_placeholder).visibility = View.VISIBLE
            } else {
                Glide
                    .with(requireActivity())
                    .load(url)
                    .into(it.findViewById(R.id.image))
                it.findViewById<TextView>(R.id.title).text = title
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.run {
            url = getString(URL, url)
            title = getString(TITLE, title)
        }
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