package com.example.ybsproject.photofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ybsproject.databinding.FragmentMainBinding
import com.example.ybsproject.databinding.FragmentPhotoBinding
import com.example.ybsproject.mainfragment.MainFragmentViewModel

class PhotoFragment: Fragment() {

    private val viewModel by viewModels<PhotoViewModel>()

    private var _binding: FragmentPhotoBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
