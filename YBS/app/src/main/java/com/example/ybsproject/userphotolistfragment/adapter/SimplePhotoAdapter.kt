package com.example.ybsproject.userphotolistfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ybsproject.databinding.LayoutPhotoSimpleBinding
import com.example.ybsproject.mainfragment.data.Post
import com.example.ybsproject.userphotolistfragment.data.SimplePhotoData

class SimplePhotoAdapter(
    private val dataset: List<SimplePhotoData>,
): RecyclerView.Adapter<SimplePhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimplePhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SimplePhotoViewHolder(LayoutPhotoSimpleBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: SimplePhotoViewHolder, position: Int) {
        holder.bind(dataset[position])
    }
}