package com.example.gdscdwp.network

import com.example.gdscdwp.domain.CatImage
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.thecatapi.com/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface CatApiService {
    @GET("v1/images/search")
    suspend fun getAll(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String,
        @Query("category_ids") category_id: Int,
    ): List<NetworkResponse>
}

object ResponseApi {
    val retrofitService: CatApiService by lazy {
        retrofit.create(CatApiService::class.java)
    }
}