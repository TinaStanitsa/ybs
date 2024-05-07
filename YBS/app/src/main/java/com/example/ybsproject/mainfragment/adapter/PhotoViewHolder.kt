package com.example.ybsproject.mainfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ybsproject.databinding.LayoutPhotoCardBinding
import com.example.ybsproject.mainfragment.data.Photo

class PhotoViewHolder(
    private val binding: LayoutPhotoCardBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Photo){
        binding.tvNameTitle.text = item.ownerName
        Glide.with(binding.tvPostPicture)
            .load(item.url)
            .into(binding.tvPostPicture)
        Glide.with(binding.tvProfilePicture)
            .load(item.ownerUrl)
            .into(binding.tvProfilePicture)
    }
}
