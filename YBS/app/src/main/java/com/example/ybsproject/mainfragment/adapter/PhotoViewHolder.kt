package com.example.ybsproject.mainfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ybsproject.databinding.LayoutPhotoCardBinding
import com.example.ybsproject.mainfragment.data.Post

class PhotoViewHolder(
    private val binding: LayoutPhotoCardBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Post, onPostClicked: (String, String, String) -> Unit){
        binding.tvNameTitle.text = item.ownerName
        Glide.with(binding.tvPostPicture)
            .load(item.url)
            .into(binding.tvPostPicture)
        Glide.with(binding.tvProfilePicture)
            .load(item.ownerUrl)
            .into(binding.tvProfilePicture)
        binding.tvTags.text = "Tags: ${item.tags}"
        binding.tvDate.text = item.dateUpload

        binding.root.setOnClickListener {
            onPostClicked(item.id, item.url, item.ownerUrl)
        }
    }
}
