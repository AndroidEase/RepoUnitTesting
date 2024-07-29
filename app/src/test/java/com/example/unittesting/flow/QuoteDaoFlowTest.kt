package com.example.unittesting.flow

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDaoFlowTest {

//    @Insert
//    suspend fun insertQuote(quote: QuoteT)
//
//    @Update
//    suspend fun updateQuote(quote: QuoteT)
//
//    @Query("DELETE FROM QuoteT")
//    suspend fun delete()
//
//    @Query("SELECT * FROM QuoteT")
//    fun getQuotes(): Flow<List<QuoteT>>
//
//    @Query("SELECT * FROM QuoteT where id = :quoteId")
//    suspend fun getQuoteById(quoteId: Int): QuoteT

}