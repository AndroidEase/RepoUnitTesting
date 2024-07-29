package com.example.unittesting.roomdatabase

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class QuoteDaoTest {

//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    lateinit var quoteDatabase: QuoteDatabase
    lateinit var quotesDao: QuoteDao

    @Before
    fun setUp(){
        hiltAndroidRule.inject()
        quotesDao = quoteDatabase.quoteDao()
    }

    @Test
    fun insertQuote_expectedSingleQuote() = runBlocking {
        val quote = QuoteT(0, "This is a test quote", "CheezyCode")
        quotesDao.insertQuote(quote)

        val result = quotesDao.getQuotes().first()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("This is a test quote", result[0].text)
    }

    @Test
    fun deleteQuote_expectedNoResults() = runBlocking {
//        val quote = Quote(0, "This is a test quote", "CheezyCode")
//        quotesDao.insertQuote(quote)
//
//        quotesDao.delete()
//
//        val result = quotesDao.getQuotes().getOrAwaitValue()
//
//        Assert.assertEquals(0, result.size)
    }


    @After
    fun tearDown(){
      //  quoteDatabase.close()
    }
}
