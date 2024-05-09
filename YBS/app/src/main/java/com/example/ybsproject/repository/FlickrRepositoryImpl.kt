package com.example.ybsproject.repository

import com.example.ybsproject.EMPTY
import com.example.ybsproject.flickr.FlickrApi
import com.example.ybsproject.flickr.PhotoResponse
import com.example.ybsproject.flickr.PostResponse
import com.example.ybsproject.flickr.UserPhotosResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://api.flickr.com/services/rest/"
private const val CONNECTION_TIMEOUT_MS: Long = 10

@Singleton
class FlickrRepositoryImpl @Inject constructor() : FlickrRepository {

    override fun getInitialPhotos(): List<PostResponse>? {
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

        return try {
            val response = api.fetchImages().execute()
            if (response.isSuccessful)
                response.body()?.photos?.photo
            else
                emptyList()
        } catch (e: Exception) {
            null
        }
    }

    override fun getPhotoInfo(photoId: String): PhotoResponse? {
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

        return try {
            val response = api.getPhotoInfo(photoId).execute()
            if (response.isSuccessful)
                response.body()
            else
                null
        } catch (e: Exception) {
            null
        }

    }

    override fun getUserPhotos(userName: String): UserPhotosResponse? {
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

        val noSpacesUserName = userName.replace("\\s".toRegex(), EMPTY) // some usernames were empty and it caused crash
        return try {
            val response = api.getUserPhotos(noSpacesUserName).execute()
            if (response.isSuccessful)
                response.body()
            else
                null
        } catch (e: Exception) {
            null
        }
    }
}
