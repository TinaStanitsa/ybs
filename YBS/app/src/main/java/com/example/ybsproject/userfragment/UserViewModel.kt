package com.example.ybsproject.userfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ybsproject.EMPTY
import com.example.ybsproject.ViewState
import com.example.ybsproject.repository.FlickrRepositoryImpl
import com.example.ybsproject.userfragment.data.UserData
import com.example.ybsproject.userfragment.data.UserDataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val simplePhotoMapper: UserDataMapper,
    private val repository: FlickrRepositoryImpl
) : ViewModel() {

    private val _photoData = MutableLiveData<ViewState<List<UserData>, Any>>()
    val photoData: LiveData<ViewState<List<UserData>, Any>> = _photoData

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
