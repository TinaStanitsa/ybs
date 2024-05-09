package com.example.ybsproject.mainfragment.data

data class Post(
    val id: String,
    val url: String,
    val title: String,
    val userName: String,
    val profilePictureUrl: String,
    val owner:String,
    val tags: String,
    val dateUpload: String
)
