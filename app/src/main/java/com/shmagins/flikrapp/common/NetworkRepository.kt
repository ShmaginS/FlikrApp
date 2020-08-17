package com.shmagins.flikrapp.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRepository(val retrofit: Retrofit) {

    public fun getThumbnails(page: Int, count: Int = 50): List<String> {
        return ArrayList<String>()
    }
}