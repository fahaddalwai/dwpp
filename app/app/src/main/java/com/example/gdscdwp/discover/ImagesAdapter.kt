package com.example.gdscdwp.discover


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdscdwp.databinding.ImageItemBinding
import com.example.gdscdwp.domain.CatImage


class ImagesAdapter(val clickListener: CatClickedListener) : PagingDataAdapter<CatImage, ImagesAdapter.ViewHolder>(CatDiffCallback()) {

    class ViewHolder private constructor(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: CatImage, clickListener: CatClickedListener) {
            binding.catImage= item
            binding.clickListener = clickListener
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



        holder.bind(pet, clickListener)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }





}

class CatDiffCallback : DiffUtil.ItemCallback<CatImage>() {

    override fun areItemsTheSame(oldItem: CatImage, newItem: CatImage): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: CatImage, newItem: CatImage): Boolean {
        return oldItem == newItem
    }





}
class CatClickedListener(val clickListener: (catUrl: String)-> Unit) {
    fun onClick(catImage: CatImage) = clickListener(catImage.url)
}