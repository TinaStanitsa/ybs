package com.example.ybsproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ybsproject.mainfragment.MainFragmentViewModel
import com.example.ybsproject.mainfragment.data.MainFragmentMapper
import com.example.ybsproject.photofragment.PhotoViewModel
import com.example.ybsproject.photofragment.data.InfoMapper
import com.example.ybsproject.repository.FlickrRepositoryImpl
import com.example.ybsproject.userfragment.UserViewModel
import com.example.ybsproject.userfragment.data.SimplePhotoMapper
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ViewModelTests {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var userViewModel: UserViewModel


    private val repository: FlickrRepositoryImpl = mockk()


    private val mainFragmentMapper: MainFragmentMapper = mockk()
    private val infoMapper: InfoMapper = mockk()
    private val simplePhotoMapper: SimplePhotoMapper = mockk()

    @Before
    fun setup() {

        mainFragmentViewModel = MainFragmentViewModel(
            mainFragmentMapper,
            repository
        )

        photoViewModel = PhotoViewModel(
            infoMapper,
            repository
        )

        userViewModel = UserViewModel(
            simplePhotoMapper,
            repository
        )

        @After
        fun afterTests() {
            unmockkAll()
        }

    }
}