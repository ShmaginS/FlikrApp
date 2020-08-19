package com.shmagins.flikrapp.di

import com.shmagins.flikrapp.main.MainActivity
import com.shmagins.flikrapp.singlephoto.SinglePhotoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector
    abstract fun bindSinglePhotoActivity(): SinglePhotoActivity
}