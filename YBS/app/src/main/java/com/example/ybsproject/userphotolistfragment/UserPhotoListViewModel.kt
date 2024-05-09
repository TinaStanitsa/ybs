package com.example.ybsproject.userphotolistfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsproject.repository.FlickrRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPhotoListViewModel @Inject constructor(
    private val repository: FlickrRepositoryImpl
): ViewModel() {

    fun getPhotosOfUser(userName : String){
        viewModelScope.launch(Dispatchers.IO) {
            val photos = repository.getUserPhotos(userName)
            photos?.let {
                val x = 5
            }
        }
    }
}