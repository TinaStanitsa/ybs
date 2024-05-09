package com.example.ybsproject.userfragment.data

import com.example.ybsproject.flickr.UserPhotosResponse
import javax.inject.Inject

class UserDataMapper @Inject constructor(
) : (UserPhotosResponse) -> List<UserData> {
    override fun invoke(data: UserPhotosResponse): List<UserData> {
        return data.photos.photo.map {
            UserData(
                photoUrl = "https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg",
                title = it.title
            )
        }
    }
}
