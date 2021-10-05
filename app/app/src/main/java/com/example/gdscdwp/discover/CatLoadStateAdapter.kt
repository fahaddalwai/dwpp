package com.example.gdscdwp.discover

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.gdscdwp.discover.CatLoadStateViewHolder

class CatLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<CatLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: CatLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): CatLoadStateViewHolder {
        return CatLoadStateViewHolder.create(parent, retry)
    }
}