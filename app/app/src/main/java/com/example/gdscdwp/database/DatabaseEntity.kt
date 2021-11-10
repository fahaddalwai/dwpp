package com.example.gdscdwp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gdscdwp.domain.CatImage


@Entity
data class DatabaseCatImage(
    @PrimaryKey
    val url: String,
    var collectionName:String=""
)


fun List<DatabaseCatImage>.asDomainModel(): List<CatImage> {
    return map {
        CatImage(

            url = it.url,
            )
    }}