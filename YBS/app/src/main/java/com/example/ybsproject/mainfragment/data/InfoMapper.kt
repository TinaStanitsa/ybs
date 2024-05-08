package com.example.ybsproject.mainfragment.data

import android.content.Context
import com.example.ybsproject.flickr.PhotoResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InfoMapper @Inject constructor(
    @ApplicationContext private val context: Context
):(PhotoResponse) -> PhotoInfo {
    override fun invoke(infoResponse: PhotoResponse): PhotoInfo {
        return PhotoInfo(
            userName = infoResponse.photo.owner?.username?: "",
            realName = infoResponse.photo.owner?.realname ?: "",
            country = infoResponse.photo.location?.country?._content ?: "",
            region = infoResponse.photo.location?.county?._content ?: "")
    }
}