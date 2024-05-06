package com.example.ybsproject.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ybsproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainFragmentViewModel>()

    private var _binding: FragmentMainBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
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
        viewModel.getPhotos()
    }

    private fun initListeners(){
        viewModel.photosLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),"Data Received", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
