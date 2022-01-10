package com.example.gdscdwp.network.request

import com.google.gson.annotations.SerializedName

data class UserSignupInfo (
    @SerializedName("name") val userName: String?,
    @SerializedName("email") val Email: String?,
    @SerializedName("password") val passWord: String?
)


