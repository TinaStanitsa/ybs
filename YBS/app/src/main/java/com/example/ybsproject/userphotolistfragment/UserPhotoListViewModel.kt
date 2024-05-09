package com.example.ybsproject.userphotolistfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsproject.photofragment.data.PhotoInfo
import com.example.ybsproject.repository.FlickrRepositoryImpl
import com.example.ybsproject.userphotolistfragment.data.SimplePhotoData
import com.example.ybsproject.userphotolistfragment.data.SimplePhotoMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPhotoListViewModel @Inject constructor(
    private val simplePhotoMapper: SimplePhotoMapper,
    private val repository: FlickrRepositoryImpl
) : ViewModel() {

    private val mutablePhotoInfoLiveData = MutableLiveData<List<SimplePhotoData>>()
    val photoInfoLiveData: LiveData<List<SimplePhotoData>> = mutablePhotoInfoLiveData

    fun getPhotosOfUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val photos = repository.getUserPhotos(userName)
            photos?.let {
                mutablePhotoInfoLiveData.postValue(simplePhotoMapper(it))
            }
        }
    }
}
