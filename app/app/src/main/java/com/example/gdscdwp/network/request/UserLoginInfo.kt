package com.example.gdscdwp.network.request

import com.google.gson.annotations.SerializedName

data class UserLoginInfo(
    @SerializedName("email") val Email: String?,
    @SerializedName("password") val passWord: String?
)