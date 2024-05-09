package com.example.ybsproject.repository


import com.example.ybsproject.flickr.PhotoResponse
import com.example.ybsproject.flickr.PostResponse
import com.example.ybsproject.flickr.UserPhotosResponse

interface FlickrRepository {

    fun getInitialPhotos(): List<PostResponse>?

    fun getPhotoInfo(photoId: String): PhotoResponse?

    fun getUserPhotos(userName: String): UserPhotosResponse?
}
