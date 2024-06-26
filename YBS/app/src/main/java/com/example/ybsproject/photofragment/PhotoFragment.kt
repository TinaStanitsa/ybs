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
import com.example.ybsproject.EMPTY
import com.example.ybsproject.PHOTO_URL
import com.example.ybsproject.POST_ID
import com.example.ybsproject.PROFILE_URL
import com.example.ybsproject.R
import com.example.ybsproject.USER_ID
import com.example.ybsproject.ViewState
import com.example.ybsproject.databinding.FragmentPhotoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private val viewModel by viewModels<PhotoViewModel>()

    private var _binding: FragmentPhotoBinding? = null

    private val binding get() = _binding!!
    private var userName = EMPTY
    private var photoId: String? = null

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

        photoId = arguments?.getString(POST_ID)
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

        viewModel.postInfo.observe(viewLifecycleOwner) {

            when (it) {
                is ViewState.Success -> {
                    binding.btnError.isVisible = false
                    binding.lottieAnimation.isVisible = false
                    binding.lottieAnimation.cancelAnimation()

                    binding.cvPhotoInfo.isVisible = true
                    binding.tvPhotoInfo.text = getString(R.string.photo_info)
                    binding.tvPhotoInfo.isVisible = true

                    binding.tvRealName.text = it.result.realName
                    binding.tvUserName.text = it.result.userName
                    userName = it.result.userName ?: EMPTY

                    it.result.title?.let { titleText ->
                        binding.tvTitle.text = getString(R.string.photo_title)

                        binding.tvTitleText.text = titleText
                        binding.tvTitle.isVisible = true
                        binding.tvTitleText.isVisible = true
                    }

                    it.result.location?.let { locationText ->
                        binding.tvLocationTitle.text = getString(R.string.photo_location)
                        binding.tvLocationText.text = locationText
                        binding.tvLocationTitle.isVisible = true
                        binding.tvLocationText.isVisible = true
                    }

                    it.result.tags?.let { tagsText ->
                        binding.tvTagsTitle.text = getString(R.string.photo_tags)
                        binding.tvTagsText.text = tagsText
                        binding.tvTagsTitle.isVisible = true
                        binding.tvTagsText.isVisible = true
                    }

                    it.result.description?.let { descriptionText ->
                        binding.tvDescriptionTitle.text = getString(R.string.description_title)
                        binding.tvDescriptionText.text = descriptionText
                        binding.tvDescriptionTitle.isVisible = true
                        binding.tvDescriptionText.isVisible = true
                    }

                    it.result.views?.let { viewsText ->
                        binding.tvViewsTitle.text = getString(R.string.views_title)
                        binding.tvViewsText.text = viewsText
                        binding.tvViewsTitle.isVisible = true
                        binding.tvViewsText.isVisible = true
                    }
                    it.result.dateUploaded?.let { dateUploadedText ->
                        binding.tvDateUploadedTitle.text = getString(R.string.date_uploaded)
                        binding.tvDateUploadedText.text = dateUploadedText
                        binding.tvDateUploadedTitle.isVisible = true
                        binding.tvDateUploadedText.isVisible = true
                    }
                }

                is ViewState.Error -> {
                    binding.btnError.text = getString(R.string.error_try_again)
                    binding.lottieAnimation.isVisible = false
                    binding.lottieAnimation.cancelAnimation()
                    binding.btnError.isVisible = true
                    binding.btnError.setOnClickListener {
                        photoId?.let { pId ->
                            viewModel.getPhotoInfo(pId)
                        }
                    }
                }

                is ViewState.Loading -> {
                    binding.btnError.isVisible = false
                    binding.lottieAnimation.isVisible = true
                    binding.lottieAnimation.playAnimation()
                }
            }


        }

        binding.clUserInfo.setOnClickListener {
            val bundle = bundleOf(
                PROFILE_URL to profilePictureUrl,
                USER_ID to userName
            )
            findNavController().navigate(R.id.action_photoFragment_to_userPhotoListFragment, bundle)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
