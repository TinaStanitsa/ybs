package com.example.ybsproject.mainfragment.data

import android.annotation.SuppressLint
import android.content.Context
import com.example.ybsproject.R
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
                profilePictureUrl = "https://farm${it.farm}.staticflickr.com/${it.server}/buddyicons/${it.owner}.jpg",
                userName = it.ownername,
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
            context.getString(R.string.today)
        } else {
            "${calculateDaysDifference(currentDate, date)}${context.getString(R.string.day_d)}"
        }
        return  formattedDate
    }

    @SuppressLint("SimpleDateFormat")
    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val dateFormat = SimpleDateFormat("yyyyMMdd")
        return dateFormat.format(date1) == dateFormat.format(date2)
    }

    private fun calculateDaysDifference(date1: Date, date2: Date): Long {
        val diffInMillis = date1.time - date2.time
        return (diffInMillis / (1000 * 60 * 60 * 24)) +1
    }
}