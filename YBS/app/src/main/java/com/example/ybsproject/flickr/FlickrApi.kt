package com.example.ybsproject.flickr

import com.example.ybsproject.BuildConfig
import com.example.ybsproject.PHOTO_ID
import com.example.ybsproject.USER_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrApi {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&tags=yorkshire&extras=owner_name,date_upload,tags&safe_search=1&api_key=${BuildConfig.API_KEY}")
    fun fetchImages(): Call<FlickrResponse>


    @GET("?method=flickr.photos.getInfo&format=json&nojsoncallback=1&api_key=${BuildConfig.API_KEY}")
    fun getPhotoInfo(
        @Query(PHOTO_ID) photoId: String
    ): Call<PhotoResponse>

    @GET("?method=flickr.people.getPhotos&format=json&nojsoncallback=1&api_key=${BuildConfig.API_KEY}&safe_search=1")
    fun getUserPhotos(
        @Query(USER_ID) userId: String
    ): Call<UserPhotosResponse>
}
