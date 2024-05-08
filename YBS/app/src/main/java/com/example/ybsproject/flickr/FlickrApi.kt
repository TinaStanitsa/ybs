package com.example.ybsproject.flickr

import com.example.ybsproject.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


interface FlickrApi {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&tags=yorkshire&extras=owner_name,date_upload,tags&safe_search=1&api_key=${BuildConfig.API_KEY}")
    fun fetchImages(): Call<FlickrResponse>


    @GET("?method=flickr.photos.getInfo&format=json&nojsoncallback=1&api_key=${BuildConfig.API_KEY}")
    fun getPhotoInfo(
        @Query("photo_id") photoId: String
    ): Call<PhotoResponse>
}
