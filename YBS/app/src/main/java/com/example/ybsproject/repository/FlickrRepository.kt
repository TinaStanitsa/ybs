package com.example.ybsproject.repository

import com.example.ybsproject.flickr.PostResponse

interface FlickrRepository {

    fun getInitialPhotos(): List<PostResponse>?
}