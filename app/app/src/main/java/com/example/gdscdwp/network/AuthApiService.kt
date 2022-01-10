package com.example.gdscdwp.network

import com.example.gdscdwp.network.dto.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://dwpp-dev.herokuapp.com/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()




private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Authorization",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MWRhZmJlOTY4NDNlYWUzYTNlNzdlOWMiLCJpYXQiOjE2NDE3NDEyODl9.yG_3U1HSP0PLOHFrI9hGUTIZ8sK9It8upBiYdEgPi-M"
            )
            .addHeader("Content-Type", "application/json")
            .method(original.method, original.body)


        val request = requestBuilder.build()
        chain.proceed(request)
    }.addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()



interface AuthApiService {

    @POST( "users")
    suspend fun createUser(
        @Body param:UserInfo
    ): User



}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

object AuthApi {
    val retrofitService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }
}



