package com.shmagins.flikrapp.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shmagins.flikrapp.di.DaggerAppComponent
import javax.inject.Inject

class MainActivityViewModel(private val app: Application) : AndroidViewModel(app) {
    @Inject lateinit var networkRepository: NetworkRepository

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun getFlikrPhotos(tag: String): List<String> {
        return emptyList()
    }
}