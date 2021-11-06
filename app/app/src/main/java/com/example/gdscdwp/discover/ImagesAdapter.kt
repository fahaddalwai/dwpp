package com.example.gdscdwp.discover


import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.example.gdscdwp.databinding.ImageItemBinding
import com.example.gdscdwp.model.CatImage
import java.util.*


import androidx.annotation.NonNull
import androidx.annotation.Nullable

import com.bumptech.glide.ListPreloader.PreloadModelProvider





class ImagesAdapter() : PagingDataAdapter<CatImage, ImagesAdapter.ViewHolder>(SleepNightDiffCallback()) {

    class ViewHolder private constructor(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: CatImage) {
            binding.catImage= item
            binding.executePendingBindings()




        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImageItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet=getItem(position)!!



        holder.bind(pet)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }





}




class SleepNightDiffCallback : DiffUtil.ItemCallback<CatImage>() {

    override fun areItemsTheSame(oldItem: CatImage, newItem: CatImage): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: CatImage, newItem: CatImage): Boolean {
        return oldItem == newItem
    }




}
