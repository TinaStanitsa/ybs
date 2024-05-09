package com.example.ybsproject.mainfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsproject.EMPTY
import com.example.ybsproject.ViewState
import com.example.ybsproject.mainfragment.data.MainFragmentMapper
import com.example.ybsproject.mainfragment.data.Post
import com.example.ybsproject.repository.FlickrRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val photoMapper: MainFragmentMapper,
    private val repository: FlickrRepositoryImpl
) : ViewModel() {
    private val _postData = MutableLiveData<ViewState<List<Post>, Any>>()
    val postData: LiveData<ViewState<List<Post>, Any>> = _postData

    fun getInitialPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            _postData.postValue(ViewState.Loading)
            val photos = repository.getInitialPhotos()
            photos?.let {
                _postData.postValue(ViewState.Success(photoMapper(it)))
            }
            if (photos == null) {
                _postData.postValue(ViewState.Error(EMPTY))
            }
        }
    }
}
