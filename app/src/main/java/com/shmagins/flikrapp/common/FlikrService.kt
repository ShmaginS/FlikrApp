package com.shmagins.flikrapp.common

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FlikrService {
    @GET("rest/?method=flickr.photos.search&api_key=74c49a3ad2cacd9bc90e67b3a530dd20&format=json&nojsoncallback=1")
    fun getResponse(@Query("text") text: String, @Query("page") page: Int, @Query("per_page") per_page: Int, @Query("type") type: String): Observable<Response>
}