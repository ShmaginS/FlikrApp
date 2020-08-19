package com.shmagins.flikrapp

import android.app.Application
import android.util.Log
import com.shmagins.flikrapp.di.DaggerAppComponent
import com.shmagins.flikrapp.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class FlikrApp : Application(), HasAndroidInjector {
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .networkModule(NetworkModule(this))
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
}