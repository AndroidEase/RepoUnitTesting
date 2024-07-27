package com.example.unittesting.mocking

import android.content.Context
import android.content.res.AssetManager
import com.example.unittesting.instrumentationtest.QuoteManager
import org.junit.Assert
import org.junit.Before
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Test
import org.mockito.Mockito.doReturn

class MockContextAssets {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var assetManager : AssetManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test() {
        val testStream = MockContextAssets::class.java.getResourceAsStream("/quotes.json")
        doReturn(assetManager).`when`(context).assets
        Mockito.`when`(context.assets.open(anyString())).thenReturn(testStream)

        val sut = QuoteManager()
        sut.populateQuoteFromAssets(context, "")
        val quote = sut.getCurrentQuote()
        Assert.assertEquals(
            "test 1",quote.text
        )
    }
}