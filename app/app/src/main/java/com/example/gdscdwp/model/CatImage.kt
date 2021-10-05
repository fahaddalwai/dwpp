package com.example.gdscdwp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catImages")
data class CatImage(
    @PrimaryKey(autoGenerate = false)
    var url: String,

)