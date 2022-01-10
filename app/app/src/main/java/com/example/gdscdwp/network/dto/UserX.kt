package com.example.gdscdwp.network.dto

data class UserX(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val dislikes: List<String>,
    val email: String,
    val likes: List<String>,
    val name: String,
    val password: String,
    val tokens: List<Token>,
    val updatedAt: String,
    val weeklyPictures: List<String>
)