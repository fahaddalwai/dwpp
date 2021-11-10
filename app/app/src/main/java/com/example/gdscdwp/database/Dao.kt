package com.example.gdscdwp.database

import androidx.paging.PagingSource
import androidx.room.*
import com.example.gdscdwp.domain.CatImage

@Dao
interface CatDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(catImages: List<DatabaseCatImage>)

    @Query(
        "SELECT * FROM DatabaseCatImage"
    )
    fun catsAllRandom(): PagingSource<Int, DatabaseCatImage>

    @Query("DELETE FROM DatabaseCatImage")
    suspend fun clearRepos()



}