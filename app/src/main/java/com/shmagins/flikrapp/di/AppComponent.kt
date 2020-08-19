package com.shmagins.flikrapp.di

import com.shmagins.flikrapp.FlikrApp
import com.shmagins.flikrapp.common.MainActivityViewModel
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class,NetworkModule::class, AppModule::class, ActivityModule::class, FragmentModule::class])
@Singleton
interface AppComponent : AndroidInjector<FlikrApp>{
    override fun inject(app: FlikrApp?)
    fun inject(viewModel: MainActivityViewModel)
}