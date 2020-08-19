package com.shmagins.flikrapp.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Response (
    @SerializedName("photos")
    @Expose
    var photos: Photos? = null,
    @SerializedName("stat")
    @Expose
    var stat: String? = null
)