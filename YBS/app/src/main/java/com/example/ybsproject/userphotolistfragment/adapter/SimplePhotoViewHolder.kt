package com.example.ybsproject.userphotolistfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ybsproject.databinding.LayoutPhotoSimpleBinding
import com.example.ybsproject.mainfragment.data.Post

class SimplePhotoViewHolder(
    private val binding: LayoutPhotoSimpleBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Post){
        binding.tvTitle.text = item.title
        binding.tvTags.text = item.tags
        Glide.with(binding.tvPostPicture)
            .load(item.url)
            .into(binding.tvPostPicture)
    }
}