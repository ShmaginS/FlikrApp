package com.shmagins.flikrapp.di

import com.shmagins.flikrapp.PandaApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: PandaApplication) {
    @Provides
    @Singleton
    fun provideApplication() = app

}