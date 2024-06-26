package com.example.ybsproject.userfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ybsproject.databinding.LayoutPhotoSimpleBinding
import com.example.ybsproject.userfragment.data.UserData

class SimplePhotoAdapter(
    private val dataset: List<UserData>,
) : RecyclerView.Adapter<SimplePhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimplePhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SimplePhotoViewHolder(
            LayoutPhotoSimpleBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: SimplePhotoViewHolder, position: Int) {
        holder.bind(dataset[position])
    }
}
