package com.example.unittesting.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quote: QuoteT)

    @Update
    suspend fun updateQuote(quote: QuoteT)

    @Query("DELETE FROM QuoteT")
    suspend fun delete()

    @Query("SELECT * FROM QuoteT")
    fun getQuotes(): LiveData<List<QuoteT>>

    @Query("SELECT * FROM QuoteT where id = :quoteId")
    suspend fun getQuoteById(quoteId: Int): QuoteT

}