package com.example.ybsproject.userphotolistfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsproject.EMPTY
import com.example.ybsproject.ViewState
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

    private val _photoData = MutableLiveData<ViewState<List<SimplePhotoData>, Any>>()
    val photoData: LiveData<ViewState<List<SimplePhotoData>,Any>> = _photoData

    fun getPhotosOfUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _photoData.postValue(ViewState.Loading)
            //sleep(1000) // uncomment it to see loading animation
            val photos = repository.getUserPhotos(userName)
            photos?.let {
                _photoData.postValue(ViewState.Success(simplePhotoMapper(it)))
            }
            if (photos == null)
                _photoData.postValue(ViewState.Error(EMPTY)) // force this to see error message
        }
    }
}
