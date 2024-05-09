package com.example.ybsproject.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ybsproject.PHOTO_URL
import com.example.ybsproject.POST_ID
import com.example.ybsproject.PROFILE_URL
import com.example.ybsproject.R
import com.example.ybsproject.USER_ID
import com.example.ybsproject.databinding.FragmentMainBinding
import com.example.ybsproject.mainfragment.adapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by viewModels<MainFragmentViewModel>()

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        if (viewModel.photosLiveData.value.isNullOrEmpty()) // so it will not get initialised every time we go back from photo fragment
            viewModel.getInitialPhotos()

        binding.tvTitle.text = getString(R.string.flickr_posts_of_yorkshire)
    }

    private fun initListeners() {
        viewModel.photosLiveData.observe(viewLifecycleOwner) {
            binding.rvPosts.layoutManager = LinearLayoutManager(context)
            binding.rvPosts.adapter = PhotoAdapter(it, ::onPostClicked, ::onProfileCLicked)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onPostClicked(postId: String, photoUrl: String, profilePictureUrl: String) {
        val bundle = bundleOf(
            POST_ID to postId,
            PHOTO_URL to photoUrl,
            PROFILE_URL to profilePictureUrl
        )
        findNavController().navigate(R.id.action_mainFragment_to_photoFragment, bundle)
    }

    private fun onProfileCLicked(userID: String, profilePictureUrl: String) {
        val bundle = bundleOf(
            USER_ID to userID,
            PROFILE_URL to profilePictureUrl
        )
        findNavController().navigate(R.id.action_mainFragment_to_userPhotoListFragment, bundle)
    }
}
