package com.example.ybsproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ybsproject.mainfragment.MainFragmentViewModel
import com.example.ybsproject.mainfragment.data.PhotoMapper
import com.example.ybsproject.photofragment.PhotoViewModel
import com.example.ybsproject.repository.FlickrRepositoryImpl
import com.example.ybsproject.userfragment.UserViewModel
import io.mockk.mockk
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ViewModelTests {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var mainViewModel: MainFragmentViewModel
    private lateinit var infoViewModel: PhotoViewModel
    private lateinit var userViewModel: UserViewModel


    private val repository: FlickrRepositoryImpl = mockk()
    private val mainFragmentMapper: PhotoMapper = mockk()

}