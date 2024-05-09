package com.example.ybsproject.photofragment.data

import com.example.ybsproject.flickr.PhotoResponse
import com.example.ybsproject.flickr.Tag
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class InfoMapper @Inject constructor(
) : (PhotoResponse) -> PhotoInfo {
    override fun invoke(infoResponse: PhotoResponse): PhotoInfo {
        return PhotoInfo(
            title = infoResponse.photo.title?._content,
            userName = infoResponse.photo.owner?.username,
            realName = infoResponse.photo.owner?.realname,
            country = infoResponse.photo.location?.country?._content,
            region = infoResponse.photo.location?.county?._content,
            tags = getTags(infoResponse.photo.tags?.tag),
            location = getLocation(
                infoResponse.photo.location?.country?._content,
                infoResponse.photo.location?.county?._content
            ),
            views = infoResponse.photo.views,
            description = infoResponse.photo.description?._content,
            dateUploaded = formatDate(infoResponse.photo.dateuploaded)
        )
    }

    private fun getTags(tagList: List<Tag>?): String? {
        return when {
            tagList.isNullOrEmpty() -> null
            else -> {
                var combinedTags = ""

                for (tag in tagList) {
                    combinedTags += tag.raw + ", "
                }

                // Remove the last comma and space
                if (combinedTags.isNotEmpty()) {
                    combinedTags = combinedTags.dropLast(2)
                }
                combinedTags
            }
        }
    }

    private fun getLocation(country: String?, county: String?): String? {
        return when {
            country != null && county != null -> "$county, $country"
            country == null && county != null -> county
            country != null && county == null -> country
            else -> null
        }
    }

    private fun formatDate(timestampString: String?): String? {
        if (timestampString == null)
            return null
        val timestamp = timestampString.toLong()

        val date = Date(timestamp * 1000L)

        val dateFormat = SimpleDateFormat("dd-MMM-yyyy")

        return dateFormat.format(date)
    }
}
