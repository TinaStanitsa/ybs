package com.example.ybsproject.photofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ybsproject.PHOTO_URL
import com.example.ybsproject.POST_ID
import com.example.ybsproject.PROFILE_URL
import com.example.ybsproject.R
import com.example.ybsproject.databinding.FragmentPhotoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private val viewModel by viewModels<PhotoViewModel>()

    private var _binding: FragmentPhotoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
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

        val photoId = arguments?.getString(POST_ID)
        val photoUrl = arguments?.getString(PHOTO_URL)
        val profilePictureUrl = arguments?.getString(PROFILE_URL)

        Glide.with(binding.ivPhoto)
            .load(photoUrl)
            .into(binding.ivPhoto)

        Glide.with(binding.tvProfilePicture)
            .load(profilePictureUrl)
            .into(binding.tvProfilePicture)

        initListeners(profilePictureUrl)
        photoId?.let {
            viewModel.getPhotoInfo(it)
        }
    }

    private fun initListeners(profilePictureUrl: String?) {
        binding.ivBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.photoInfoLiveData.observe(viewLifecycleOwner) {


            binding.tvPhotoInfo.text = getString(R.string.photo_info)
            binding.tvPhotoInfo.isVisible = true

            binding.tvRealName.text = it.realName
            binding.tvUserName.text = it.userName

            it.title?.let { titleText ->
                binding.tvTitle.text = getString(R.string.photo_title)

                binding.tvTitleText.text = titleText
                binding.tvTitle.isVisible = true
                binding.tvTitleText.isVisible = true
            }

            it.location?.let { locationText ->
                binding.tvLocationTitle.text = getString(R.string.photo_location)
                binding.tvLocationText.text = locationText
                binding.tvLocationTitle.isVisible = true
                binding.tvLocationText.isVisible = true
            }

            it.tags?.let { tagsText ->
                binding.tvTagsTitle.text = getString(R.string.photo_tags)
                binding.tvTagsText.text = tagsText
                binding.tvTagsTitle.isVisible = true
                binding.tvTagsText.isVisible = true
            }

        }

        binding.clUserInfo.setOnClickListener {
            val bundle = bundleOf(
                PROFILE_URL to profilePictureUrl
            )
            findNavController().navigate(R.id.action_photoFragment_to_userPhotoListFragment, bundle)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
