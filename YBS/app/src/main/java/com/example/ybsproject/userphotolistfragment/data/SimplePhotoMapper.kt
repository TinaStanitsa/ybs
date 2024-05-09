package com.example.ybsproject.userphotolistfragment.data

import com.example.ybsproject.EMPTY
import com.example.ybsproject.flickr.Tag
import com.example.ybsproject.flickr.UserPhotosResponse
import javax.inject.Inject

class SimplePhotoMapper @Inject constructor(

):(UserPhotosResponse) -> List<SimplePhotoData> {
    override fun invoke(data: UserPhotosResponse): List<SimplePhotoData> {
        return data.photos.photo.map {
            SimplePhotoData(
                photoUrl = "https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg",
                title = it.title
            )
        }
    }

}