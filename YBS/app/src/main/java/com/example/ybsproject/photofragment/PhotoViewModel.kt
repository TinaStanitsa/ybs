package com.example.ybsproject.photofragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsproject.EMPTY
import com.example.ybsproject.ViewState
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

    private val _postInfo = MutableLiveData<ViewState<PhotoInfo, Any>>()
    val postInfo: LiveData<ViewState<PhotoInfo, Any>> = _postInfo
    fun getPhotoInfo(photoId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _postInfo.postValue(ViewState.Loading)
            val photoInfo = repository.getPhotoInfo(photoId)
            photoInfo?.let {
                _postInfo.postValue(ViewState.Success(infoMapper(it)))
            }
            if (photoInfo == null)
                _postInfo.postValue(ViewState.Error(EMPTY))

        }
    }
}
