package com.example.ybsproject.repository

import com.example.ybsproject.flickr.PhotoResponse

interface FlickrRepository {

    fun getInitialPhotos(): List<PhotoResponse>?
}