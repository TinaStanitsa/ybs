package com.example.ybsproject.mainfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ybsproject.R
import com.example.ybsproject.databinding.LayoutPhotoCardBinding
import com.example.ybsproject.mainfragment.data.Post

class PhotoViewHolder(
    private val binding: LayoutPhotoCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Post,
        onPostClicked: (String, String, String) -> Unit,
        onProfileCLicked: (String, String) -> Unit
    ) {
        binding.tvNameTitle.text = item.userName
        Glide.with(binding.tvPostPicture)
            .load(item.url)
            .into(binding.tvPostPicture)
        Glide.with(binding.tvProfilePicture)
            .load(item.profilePictureUrl)
            .into(binding.tvProfilePicture)
        binding.tvTags.text = item.tags
        binding.tvDate.text = item.dateUpload

        binding.root.setOnClickListener {
            onPostClicked(item.id, item.url, item.profilePictureUrl)
        }

        binding.tvProfilePicture.setOnClickListener {
            onProfileCLicked(item.owner, item.profilePictureUrl)
        }
        binding.tvNameTitle.setOnClickListener {
            onProfileCLicked(item.userName, item.profilePictureUrl)
        }
        binding.tvTagsTitle.text = binding.tvTagsTitle.context.getString(R.string.photo_tags)
    }
}
