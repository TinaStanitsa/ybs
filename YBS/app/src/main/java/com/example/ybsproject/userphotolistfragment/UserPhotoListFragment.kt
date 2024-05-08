package com.example.ybsproject.userphotolistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ybsproject.databinding.FragmentPhotoBinding
import com.example.ybsproject.databinding.FragmentUserPhotoListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPhotoListFragment : Fragment() {
    private  val  viewModel by viewModels<UserPhotoListViewModel>()
    private var _binding: FragmentUserPhotoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserPhotoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}