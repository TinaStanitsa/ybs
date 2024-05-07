package com.example.ybsproject.mainfragment.data

import android.content.Context
import com.example.ybsproject.flickr.PostResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class PhotoMapper @Inject constructor(
    @ApplicationContext private val context: Context
): (List<PostResponse>) -> List<Post> {
    override fun invoke(data: List<PostResponse>): List<Post> {
       return data.map {
            Post(
                id = it.id,
                url = "https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg",
                title = it.title,
                owner = it.owner,
                ownerUrl = "https://farm${it.farm}.staticflickr.com/${it.server}/buddyicons/${it.owner}.jpg",
                ownerName = it.ownername,
                tags = it.tags,
                dateUpload = setCorrectDate(it.dateupload)
            )
        }
    }
    private fun setCorrectDate(dateF: String): String{
        val timestamp = dateF.toLong() * 1000 // convert seconds to milliseconds

        val currentDate = Date()

        val date = Date(timestamp)

        val formattedDate = if (isSameDay(currentDate, date)) {
            "Today"
        } else {
            "${calculateDaysDifference(currentDate, date)}d"
        }
        return  formattedDate
    }

    fun isSameDay(date1: Date, date2: Date): Boolean {
        val dateFormat = SimpleDateFormat("yyyyMMdd")
        return dateFormat.format(date1) == dateFormat.format(date2)
    }

    fun calculateDaysDifference(date1: Date, date2: Date): Long {
        val diffInMillis = date1.time - date2.time
        return (diffInMillis / (1000 * 60 * 60 * 24)) +1
    }
}