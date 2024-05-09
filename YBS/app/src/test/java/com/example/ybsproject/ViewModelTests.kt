package com.example.ybsproject

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ybsproject.flickr.PhotoResponse
import com.example.ybsproject.flickr.UserPhotosResponse
import com.example.ybsproject.mainfragment.MainFragmentViewModel
import com.example.ybsproject.mainfragment.data.MainFragmentMapper
import com.example.ybsproject.mainfragment.data.Post
import com.example.ybsproject.photofragment.PhotoViewModel
import com.example.ybsproject.photofragment.data.InfoMapper
import com.example.ybsproject.photofragment.data.PhotoInfo
import com.example.ybsproject.repository.FlickrRepositoryImpl
import com.example.ybsproject.userfragment.UserViewModel
import com.example.ybsproject.userfragment.data.UserData
import com.example.ybsproject.userfragment.data.UserDataMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals


@RunWith(JUnit4::class)
class ViewModelTests {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var userViewModel: UserViewModel


    private val repository: FlickrRepositoryImpl = mockk(relaxed = true)


    private val mainFragmentMapper: MainFragmentMapper = mockk(relaxed = true)
    private val infoMapper: InfoMapper = mockk(relaxed = true)
    private val simplePhotoMapper: UserDataMapper = mockk(relaxed = true)
    private val userPhotosResponse: UserPhotosResponse = mockk(relaxed = true)
    private val photoResponse: PhotoResponse = mockk(relaxed = true)
    private val photoInfo: PhotoInfo = mockk(relaxed = true)

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

    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `when repository getInitialPhotos returns success, we map with success`() {
        coEvery { repository.getInitialPhotos() } returns emptyList()
        every { mainFragmentMapper.invoke(emptyList()) } returns emptyList()

        mainFragmentViewModel.getInitialPhotos()

        verify(exactly = 1) { repository.getInitialPhotos() }
        assertEquals(mainFragmentViewModel.postData.value, ViewState.Success(emptyList<Post>()))
    }

    @Test
    fun `when repository getInitialPhotos returns error, we set viewstate as error`() {
        coEvery { repository.getInitialPhotos() } returns null

        mainFragmentViewModel.getInitialPhotos()
        verify(exactly = 1) { repository.getInitialPhotos() }
        assertEquals(mainFragmentViewModel.postData.value, ViewState.Error(EMPTY))
    }

    @Test
    fun `when repository getPhotoInfo returns success, we map with success`() {
        coEvery { repository.getPhotoInfo(EMPTY) } returns photoResponse
        every { infoMapper.invoke(photoResponse) } returns photoInfo

        photoViewModel.getPhotoInfo(EMPTY)

        verify(exactly = 1) { repository.getPhotoInfo(EMPTY) }
        assertEquals(photoViewModel.postInfo.value, ViewState.Success(photoInfo))
    }

    @Test
    fun `when repository getPhotoInfo returns error, we map with error`() {
        coEvery { repository.getPhotoInfo(EMPTY) } returns null

        photoViewModel.getPhotoInfo(EMPTY)

        verify(exactly = 1) { repository.getPhotoInfo(EMPTY) }
        assertEquals(photoViewModel.postInfo.value, ViewState.Error(EMPTY))
    }

    @Test
    fun `when repository getUserPhotos returns success, we map with success`() {
        coEvery { repository.getUserPhotos(EMPTY) } returns userPhotosResponse
        every { simplePhotoMapper.invoke(userPhotosResponse) } returns emptyList()

        userViewModel.getPhotosOfUser(EMPTY)

        verify(exactly = 1) { repository.getUserPhotos(EMPTY) }
        assertEquals(userViewModel.photoData.value, ViewState.Success(emptyList<UserData>()))
    }

    @Test
    fun `when repository getUserPhotos returns error, we map with error`() {
        coEvery { repository.getUserPhotos(EMPTY) } returns null

        userViewModel.getPhotosOfUser(EMPTY)

        verify(exactly = 1) { repository.getUserPhotos(EMPTY) }
        assertEquals(userViewModel.photoData.value, ViewState.Error(EMPTY))
    }
}