package com.example.unittesting.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteT(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text : String,
    val author : String
)