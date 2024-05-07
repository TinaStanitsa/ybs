package com.example.ybsproject.flickr

import com.example.ybsproject.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface FlickrApi {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&tags=yorkshire&extras=owner_name&safe_search=1&api_key=ddc17e759a9fe11f5c10e7fe52a0c065")
    fun fetchImages(): Call<FlickrResponse>
}
