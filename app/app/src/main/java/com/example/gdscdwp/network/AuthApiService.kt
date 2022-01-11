package com.example.gdscdwp.network

import com.example.gdscdwp.network.dto.User
import com.example.gdscdwp.network.dto.UserX
import com.example.gdscdwp.network.request.UserLoginInfo
import com.example.gdscdwp.network.request.UserSignupInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://dwpp-dev.herokuapp.com/"

private const val AUTH_ID="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MWRhZmJlOTY4NDNlYWUzYTNlNzdlOWMiLCJpYXQiOjE2NDE3NDEyODl9.yG_3U1HSP0PLOHFrI9hGUTIZ8sK9It8upBiYdEgPi-M"


private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", AUTH_ID)
            .addHeader("Content-Type", "application/json")
            .method(original.method, original.body)
            val request = requestBuilder.build()
        chain.proceed(request)
    }.addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()



interface AuthApiService {


    @POST("users")
    suspend fun createUser(
        @Body param: UserSignupInfo
    ): User

    @POST("users/login")
    suspend fun loginUser(
        @Body param: UserLoginInfo
    ): User

    @GET("users/{userId}")
    suspend fun getUserById(
        @Path("userId") id: String
    ): UserX


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



