package com.example.ybsproject.userfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ybsproject.databinding.LayoutPhotoSimpleBinding
import com.example.ybsproject.userfragment.data.SimplePhotoData

class SimplePhotoViewHolder(
    private val binding: LayoutPhotoSimpleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SimplePhotoData) {
        binding.tvTitle.text = item.title
        Glide.with(binding.tvPostPicture)
            .load(item.photoUrl)
            .into(binding.tvPostPicture)
    }
}
