package com.shmagins.flikrapp.di

import com.shmagins.flikrapp.FlikrApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: FlikrApp) {
    @Provides
    @Singleton
    fun provideApplication() = app

}