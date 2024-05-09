package com.example.ybsproject.flickr




data class FlickrResponse(
    val photos: PostData
)

data class PostData(
    val page: Int,
    val photo: List<PostResponse>
)

// have to give specific name, same as json response
data class PostResponse(
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

// USER INFO
data class PhotoResponse(
    val photo: Photo
)

data class Photo(
    val id: String,
    val dateuploaded: String?,
    val owner: Owner?,
    val title: Title?,
    val description: Description?,
    val views: String?,
    val tags: Tags?,
    val location: Location?,
)

data class Owner(
    val nsid: String,
    val username: String?,
    val realname: String?,
    val location: String?,
    val iconserver: String,
    val iconfarm: Int,
    val path_alias: String,
)


data class Title(
    val _content: String
)

data class Description(
    val _content: String
)


data class Tags(
    val tag: List<Tag>
)

data class Tag(
    val raw: String,
)

data class Location(
    val county: County,
    val region: Region,
    val country: Country,
)


data class County(
    val _content: String
)

data class Region(
    val _content: String
)

data class Country(
    val _content: String
)


// USER PHOTOS
data class UserPhotosResponse(
    val photos: UserPhotos,
)

data class UserPhotos(
    val photo: List<UserPhoto>
)

data class UserPhoto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)