package com.example.ybsproject.photofragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsproject.photofragment.data.InfoMapper
import com.example.ybsproject.photofragment.data.PhotoInfo
import com.example.ybsproject.repository.FlickrRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val infoMapper: InfoMapper,
    private val repository: FlickrRepositoryImpl
) : ViewModel() {

    private val mutablePhotoInfoLiveData = MutableLiveData<PhotoInfo>()
    val photoInfoLiveData: LiveData<PhotoInfo> = mutablePhotoInfoLiveData
    fun getPhotoInfo(photoId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val photoInfo = repository.getPhotoInfo(photoId)
            photoInfo?.let {
                mutablePhotoInfoLiveData.postValue(infoMapper(it))
            }

        }
    }
}
