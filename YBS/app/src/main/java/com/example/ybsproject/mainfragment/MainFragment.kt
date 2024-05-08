package com.example.ybsproject.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        viewModel.getInitialPhotos()
    }

    private fun initListeners(){
        viewModel.photosLiveData.observe(viewLifecycleOwner){
            binding.rvPosts.layoutManager = LinearLayoutManager(context)
            binding.rvPosts.adapter = PhotoAdapter(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
