package com.example.gdscdwp.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val user: UserX,
    val token: String

)