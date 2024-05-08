package com.example.ybsproject.photofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ybsproject.databinding.FragmentMainBinding
import com.example.ybsproject.databinding.FragmentPhotoBinding
import com.example.ybsproject.mainfragment.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private val viewModel by viewModels<PhotoViewModel>()

    private var _binding: FragmentPhotoBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    // private val photoID: String? = null

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

        val photoID = arguments?.getString("post_id")
        val photoUrl = arguments?.getString("photo_url")

        Glide.with(binding.ivPhoto)
            .load(photoUrl)
            .into(binding.ivPhoto)

        initListeners()
    }

    private fun initListeners() {
        binding.ivBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
