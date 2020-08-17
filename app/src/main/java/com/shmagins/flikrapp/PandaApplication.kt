package com.shmagins.flikrapp

import android.app.Application
import android.util.Log
import com.shmagins.flikrapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class PandaApplication : Application(), HasAndroidInjector {
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
}