package com.example.gdscdwp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gdscdwp.network.CatApiService
import com.example.gdscdwp.database.CatDatabase
import com.example.gdscdwp.domain.CatImage
import kotlinx.coroutines.flow.Flow

class Repository(private val service: CatApiService,
                 private val database: CatDatabase
) {



    fun getSearchResultStream(query: String): Flow<PagingData<CatImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSource(service, query) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}