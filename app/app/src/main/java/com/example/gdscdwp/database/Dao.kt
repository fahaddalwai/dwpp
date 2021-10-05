package com.example.gdscdwp.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.gdscdwp.model.CatImage

@Dao
interface CatDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(catImages: List<CatImage>)

    @Query(
        "SELECT * FROM catImages"
    )
    fun catsAllRandom(): PagingSource<Int, CatImage>

    @Query("DELETE FROM catImages")
    suspend fun clearRepos()



}