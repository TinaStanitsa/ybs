package com.example.ybsproject.mainfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsproject.mainfragment.data.PhotoMapper
import com.example.ybsproject.mainfragment.data.Post
import com.example.ybsproject.repository.FlickrRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val mapper: PhotoMapper,
    private val repository: FlickrRepositoryImpl
) : ViewModel() {
    private val mutablePhotosLiveData = MutableLiveData<List<Post>>()
    val photosLiveData: LiveData<List<Post>> = mutablePhotosLiveData

    fun getInitialPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            val photos = repository.getInitialPhotos()
            photos?.let { mutablePhotosLiveData.postValue(mapper(it)) }
        }
    }

}
