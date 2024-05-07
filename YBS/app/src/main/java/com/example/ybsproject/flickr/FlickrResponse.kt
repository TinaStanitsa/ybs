package com.example.ybsproject.flickr




data class FlickrResponse(
    val photos: PhotosMetaData
)

data class PhotosMetaData(
    val page: Int,
    val photo: List<PhotoResponse>
)

// have to give specific name, same as json response
data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val ownername:String,
    val tags: String,
    val dateupload: String
)