package com.shmagins.flikrapp.common

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.shmagins.flikrapp.di.DaggerAppComponent
import com.shmagins.flikrapp.di.NetworkModule
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class MainActivityViewModel(private val app: Application) : AndroidViewModel(app) {
    @Inject lateinit var networkRepository: NetworkRepository
    var page = 1
    val perPage = 50
    var text: String = "panda"
    var type = "b"
    val subject: PublishSubject<ReadyPhoto> = PublishSubject.create()

    init {
        DaggerAppComponent
            .builder()
            .networkModule(NetworkModule(app))
            .build()
            .inject(this)
    }

    fun getPhotos(): Observable<ReadyPhoto> = subject

    fun searchTextChanged(text: String): Observable<ReadyPhoto> {
        this.text = text
        return networkRepository
            .getPhotos(text, page, perPage, type)
    }


    fun loadNextPage(): Observable<ReadyPhoto>{
        page++
        return networkRepository
            .getPhotos(text, page, perPage, type)
    }

    companion object {
        const val CLEAR = 0
    }
}

