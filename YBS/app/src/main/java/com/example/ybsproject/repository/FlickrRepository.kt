package com.example.ybsproject.repository


import com.example.ybsproject.flickr.PhotoResponse
import com.example.ybsproject.flickr.PostResponse

interface FlickrRepository {

    fun getInitialPhotos(): List<PostResponse>?

    fun getPhotoInfo(photoId: String): PhotoResponse?
}