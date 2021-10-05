package com.example.gdscdwp.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.gdscdwp.R
import com.example.gdscdwp.databinding.CatsLoadStateFooterBinding


class CatLoadStateViewHolder(
    private val binding: CatsLoadStateFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CatLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cats_load_state_footer, parent, false)
            val binding = CatsLoadStateFooterBinding.bind(view)
            return CatLoadStateViewHolder(binding, retry)
        }
    }
}