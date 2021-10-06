package com.example.gdscdwp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.gdscdwp.api.CatApiService
import com.example.gdscdwp.model.CatImage
import retrofit2.HttpException
import java.io.IOException

private const val API_STARTING_PAGE_INDEX = 1
private const val NETWORK_PAGE_SIZE = 30
class PagingSource(
    private val service: CatApiService,
    private val query: String
) : PagingSource<Int, CatImage>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImage> {
        val position = params.key ?: API_STARTING_PAGE_INDEX
        var apiQuery=1

        when(query){
            "boxes"->apiQuery=5
            "clothes"->apiQuery=15
            "hats"->apiQuery=1
            "sinks"->apiQuery=14
            "space"->apiQuery=2
            "sunglasses"->apiQuery=4
            "ties"->apiQuery=7
        }

        return try {

            val response = service.getAll(NETWORK_PAGE_SIZE,position,"DESC",apiQuery)
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request

                    position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == API_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, CatImage>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}