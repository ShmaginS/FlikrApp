package com.shmagins.flikrapp.di

import android.content.Context
import com.shmagins.flikrapp.common.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val appContext: Context) {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(retrofit: Retrofit): NetworkRepository {
        return NetworkRepository(retrofit)
    }

}