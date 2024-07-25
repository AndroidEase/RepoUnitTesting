package com.example.unittesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.unittesting.instrumentationtest.Quote
import com.example.unittesting.instrumentationtest.QuoteManager
import com.google.gson.JsonSyntaxException
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException
import org.junit.Assert.*

class QuoteManagerTest {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets() {
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuoteFromAssets(context, "aa")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateQuoteFromAssets_InvalidJson_expected_Exception() {
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuoteFromAssets(context, "malformed.json")
    }

    @Test
    fun populateQuoteFromAssets_ValidJson_expected_Count() {
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuoteFromAssets(context, "quotes.json")
        println("Quote::" + quoteManager.quoteList.size)

        assertEquals(3, quoteManager.quoteList.size)
    }

    @Test
    fun testPreviousQuote_expected_CorrectQuote() {
        val quoteManager = QuoteManager()
        quoteManager.populateQuotes(arrayOf(
            Quote("This is first quote","1"),
            Quote("This is second quote","2"),
            Quote("This is third quote", "3")
        ))
        val quote = quoteManager.getPreviousQuote()
        assertEquals("1", quote.author)
    }

    @Test
    fun testNextQuote_expected_CorrectQuote() {
        val quoteManager = QuoteManager()
        quoteManager.populateQuotes(arrayOf(
            Quote("This is first quote","1"),
            Quote("This is second quote","2"),
            Quote("This is third quote", "3")
        ))
        val quote = quoteManager.getNextQuote()
        assertEquals("2", quote.author)
    }
}