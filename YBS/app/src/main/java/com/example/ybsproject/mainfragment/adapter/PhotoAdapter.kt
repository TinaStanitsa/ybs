package com.example.ybsproject.mainfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ybsproject.databinding.LayoutPhotoCardBinding
import com.example.ybsproject.mainfragment.data.Post

class PhotoAdapter(private val dataset: List<Post>): RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PhotoViewHolder(LayoutPhotoCardBinding.inflate(layoutInflater,parent,false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(dataset[position])
    }
}