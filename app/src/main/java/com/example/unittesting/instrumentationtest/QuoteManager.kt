package com.example.unittesting.instrumentationtest

import android.content.Context
import com.google.gson.Gson

class QuoteManager {
    var quoteList = emptyArray<Quote>()
    var currentIndex = 0

    fun populateQuoteFromAssets(context :Context, fileName : String) {
        val inputStream = context.assets.open(fileName)
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        quoteList = gson.fromJson(json, Array<Quote>::class.java)
    }

    fun populateQuotes(quotes : Array<Quote>) {
        quoteList = quotes
    }

    fun getCurrentQuote() : Quote{
        return quoteList[currentIndex]
    }

    fun getNextQuote() : Quote {
        println("Quote::Index" + currentIndex)

        if(currentIndex == (quoteList.size - 1)) return quoteList[currentIndex]
        return quoteList[++currentIndex]
    }

    fun getPreviousQuote() : Quote {
        println("Quote::Index" + currentIndex)
        if(currentIndex == 0) {
            return quoteList[currentIndex]
        }

        if(currentIndex == (quoteList.size -1)) return quoteList[currentIndex]
        return quoteList[--currentIndex]
    }


}