package com.example.ybsproject.userphotolistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ybsproject.PROFILE_URL
import com.example.ybsproject.R
import com.example.ybsproject.USER_ID
import com.example.ybsproject.databinding.FragmentUserPhotoListBinding
import com.example.ybsproject.mainfragment.adapter.PhotoAdapter
import com.example.ybsproject.userphotolistfragment.adapter.SimplePhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPhotoListFragment : Fragment() {
    private val viewModel by viewModels<UserPhotoListViewModel>()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            })

        val profilePictureUrl = arguments?.getString(PROFILE_URL)
        val userName = arguments?.getString(USER_ID)

        Glide.with(binding.tvProfilePicture)
            .load(profilePictureUrl)
            .into(binding.tvProfilePicture)
        initListeners()

        userName?.let {
            binding.tvUserName.text = it
            viewModel.getPhotosOfUser(userName)
        }

        binding.tvPreviousPosts.text = getString(R.string.previous_posts)


    }

    private fun initListeners() {
        binding.ivBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.photoInfoLiveData.observe(viewLifecycleOwner){
            binding.rvPosts.layoutManager = LinearLayoutManager(context)
            binding.rvPosts.adapter = SimplePhotoAdapter(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}