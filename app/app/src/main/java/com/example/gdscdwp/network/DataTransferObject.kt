package com.example.gdscdwp.network

import androidx.lifecycle.Transformations.map
import com.example.gdscdwp.database.DatabaseCatImage
import com.example.gdscdwp.domain.CatImage
import com.squareup.moshi.JsonClass





@JsonClass(generateAdapter = true)
data class NetworkResponse(
    val url: String, )


/**
 * Convert Network results to database objects
 */
fun List<NetworkResponse>.asDatabaseModel(): List<DatabaseCatImage> {
    return map{
        DatabaseCatImage(
            url = it.url,
            )
    }
}

fun List<NetworkResponse>.asDomainModel():List<CatImage>{
    return map {
        CatImage(
            url = it.url,
        )
    }
}

