package com.example.ybsproject.flickr

import retrofit2.Call
import retrofit2.http.GET


interface FlickrApi {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&tags=yorkshire&extras=owner_name,date_upload,tags&safe_search=1&api_key=ddc17e759a9fe11f5c10e7fe52a0c065")
    fun fetchImages(): Call<FlickrResponse>
}
