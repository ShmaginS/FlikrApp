package com.shmagins.flikrapp.common

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit

class NetworkRepository(private val retrofit: Retrofit) {

    private val flikrService: FlikrService = retrofit.create(FlikrService::class.java)

    fun getPhotos(text: String, page: Int, perPage: Int, type: String): Observable<ReadyPhoto> =
        flikrService.getResponse(text, page, perPage, type)
            .subscribeOn(Schedulers.newThread())
            .flatMap { response: Response? -> Observable.just(response?.photos) }
            .flatMap { photos: Photos? -> Observable.fromIterable(photos?.photo) }
            .map { photo: Photo? -> ReadyPhoto(
                "https://farm${photo?.farm}.staticflickr.com/${photo?.server}/${photo?.id}_${photo?.secret}_${type}.jpg",
                photo?.title ?: ""
            ) }

}