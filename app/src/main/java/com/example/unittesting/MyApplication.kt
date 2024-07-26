package com.example.unittesting

import android.app.Application
import com.example.unittesting.api.ProductRepository
import com.example.unittesting.api.ProductsAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    lateinit var productsAPI: ProductsAPI
    lateinit var productRepository: ProductRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()

        productsAPI = retrofit.create(ProductsAPI::class.java)
        productRepository = ProductRepository(productsAPI)
    }
}