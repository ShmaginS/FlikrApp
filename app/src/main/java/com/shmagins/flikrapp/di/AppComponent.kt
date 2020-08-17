package com.shmagins.flikrapp.di

import com.shmagins.flikrapp.PandaApplication
import com.shmagins.flikrapp.common.MainActivityViewModel
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class,NetworkModule::class, AppModule::class, ActivityModule::class, FragmentModule::class])
@Singleton
interface AppComponent : AndroidInjector<PandaApplication>{
    override fun inject(app: PandaApplication?)
    fun inject(viewModel: MainActivityViewModel)
}