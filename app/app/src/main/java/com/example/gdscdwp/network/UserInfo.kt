package com.example.gdscdwp.network

import com.google.gson.annotations.SerializedName

data class UserInfo (
    @SerializedName("name") val userName: String?,
    @SerializedName("email") val Email: String?,
    @SerializedName("password") val passWord: String?
)
