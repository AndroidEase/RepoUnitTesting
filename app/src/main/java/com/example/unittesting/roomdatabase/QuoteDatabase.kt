package com.example.unittesting.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuoteT::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}