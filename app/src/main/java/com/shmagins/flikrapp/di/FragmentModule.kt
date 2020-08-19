package com.shmagins.flikrapp.di

import com.shmagins.flikrapp.main.MainActivity
import com.shmagins.flikrapp.photolist.PhotoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun bindPhotoListFragment():PhotoListFragment
}