package com.example.ybsproject.mainfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsproject.flickr.FlickrApi
import com.example.ybsproject.flickr.FlickrResponse
import com.example.ybsproject.flickr.PhotosMetaData
import com.example.ybsproject.mainfragment.data.Photo
import com.example.ybsproject.photofragment.PhotoViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor() : ViewModel() {
    private val mutablePhotosLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> = mutablePhotosLiveData

    fun getPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            val BASE_URL = "https://api.flickr.com/services/rest/"
            val CONNECTION_TIMEOUT_MS: Long = 10
            val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create()
                    )
                )
                .client(
                    OkHttpClient.Builder().connectTimeout(
                        CONNECTION_TIMEOUT_MS,
                        TimeUnit.SECONDS
                    ).addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
                )

                .build()
                .create(FlickrApi::class.java)

            api.fetchImages().enqueue(object : Callback<FlickrResponse> {

                override fun onResponse(
                    call: Call<FlickrResponse>,
                    response: Response<FlickrResponse>
                ) {
                    Log.d("success----------------------", "success")
                    mutablePhotosLiveData.postValue(emptyList())
                }

                override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                    Log.d("failxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "fail")
                }
            })
        }
    }
}
