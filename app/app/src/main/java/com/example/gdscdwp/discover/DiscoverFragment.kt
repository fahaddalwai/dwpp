package com.example.gdscdwp.discover


import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdscdwp.R
import com.example.gdscdwp.api.ResponseApi
import com.example.gdscdwp.data.Repository
import com.example.gdscdwp.database.CatDatabase.Companion.getInstance
import com.example.gdscdwp.databinding.FragmentDiscoverBinding
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class DiscoverFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverBinding
    private lateinit var viewModel: DiscoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false)




        val viewModelFactory = DiscoverViewModelFactory(this, Repository(
            ResponseApi.retrofitService,
            getInstance(requireContext())
        )
        )

        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(DiscoverViewModel::class.java)      //define instance of viewmodel using provider







        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)


        //val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS


        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the ViewModel
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.list.layoutManager=manager


        // bind the state
        binding.bindState(
            uiState = viewModel.state,
            uiActions = viewModel.accept
        )
        return binding.root
    }


    private fun FragmentDiscoverBinding.bindState(
        uiState: StateFlow<UiState>,
        uiActions: (UiAction) -> Unit
    ) {
        val imagesAdapter = ImagesAdapter()

        list.adapter = imagesAdapter.withLoadStateHeaderAndFooter(
            header = CatLoadStateAdapter { imagesAdapter.retry() },
            footer = CatLoadStateAdapter { imagesAdapter.retry() }
        )
        bindSearch(
            uiState = uiState,
            onQueryChanged = uiActions
        )
        bindList(
            imagesAdapter = imagesAdapter,
            uiState = uiState,
            onScrollChanged = uiActions
        )
    }

    private fun FragmentDiscoverBinding.bindSearch(
        uiState: StateFlow<UiState>,
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        searchCat.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRepoListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }
        searchCat.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            uiState
                .map { it.query }
                .distinctUntilChanged()
                .collect(searchCat::setText)
        }
    }

    private fun FragmentDiscoverBinding.updateRepoListFromInput(onQueryChanged: (UiAction.Search) -> Unit) {
        searchCat.text?.trim().let {
            if (it != null) {
                if (it.isNotEmpty()) {
                    list.scrollToPosition(0)
                    onQueryChanged(UiAction.Search(query = it.toString()))
                }
            }
        }
    }

    private fun FragmentDiscoverBinding.bindList(
        imagesAdapter: ImagesAdapter,
        uiState: StateFlow<UiState>,
        onScrollChanged: (UiAction.Scroll) -> Unit
    ) {
        retryButton.setOnClickListener { imagesAdapter.retry() }
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy != 0) onScrollChanged(UiAction.Scroll(currentQuery = uiState.value.query))
            }
        })
        val notLoading = imagesAdapter.loadStateFlow
            // Only emit when REFRESH LoadState for RemoteMediator changes.
            .distinctUntilChangedBy { it.refresh }
            // Only react to cases where Remote REFRESH completes i.e., NotLoading.
            .map { it.refresh is LoadState.NotLoading }

        val hasNotScrolledForCurrentSearch = uiState
            .map { it.hasNotScrolledForCurrentSearch }
            .distinctUntilChanged()

        val shouldScrollToTop = combine(
            notLoading,
            hasNotScrolledForCurrentSearch,
            Boolean::and
        )
            .distinctUntilChanged()

        val pagingData = uiState
            .map { it.pagingData }
            .distinctUntilChanged()

        lifecycleScope.launch {
            combine(shouldScrollToTop, pagingData, ::Pair)
                // Each unique PagingData should be submitted once, take the latest from
                // shouldScrollToTop
                .distinctUntilChangedBy { it.second }
                .collectLatest { (shouldScroll, pagingData) ->
                    imagesAdapter.submitData(pagingData)
                    // Scroll only after the data has been submitted to the adapter,
                    // and is a fresh search
                    if (shouldScroll) list.scrollToPosition(0)
                }
        }

        lifecycleScope.launch {
            imagesAdapter.loadStateFlow.collect { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && imagesAdapter.itemCount == 0
                // show empty list
                emptyList.isVisible = isListEmpty
                // Only show the list if refresh succeeds.
                list.isVisible = !isListEmpty
                // Show loading spinner during initial load or refresh.
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                retryButton.isVisible = loadState.source.refresh is LoadState.Error

                // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        activity,
                        "\uD83D\uDE28 Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }




}


