package com.example.unittesting.repository

import com.example.unittesting.api.ProductRepository
import com.example.unittesting.api.ProductsAPI
import com.example.unittesting.models.ProductListItem
import com.example.unittesting.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Test
import retrofit2.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ProductRepositoryTest {

    @Mock
    lateinit var productsAPI: ProductsAPI

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetProducts_EmptyList() = runTest {
        Mockito.`when`(productsAPI.getProducts()).thenReturn(Response.success(emptyList()))

        val sut = ProductRepository(productsAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Success)
        Assert.assertEquals(0, result.data?.size)
    }

    @Test
    fun testGetProducts_expectedProductList() = runTest {

        val productList = listOf<ProductListItem>(
            ProductListItem("","desc1", 1, "", 10.0, "Title1"),
            ProductListItem("","desc2", 1, "", 20.0, "Title2")
        )
        Mockito.`when`(productsAPI.getProducts()).thenReturn(Response.success(productList))

        val sut = ProductRepository(productsAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Success)
        Assert.assertEquals(2, result.data?.size)
        Assert.assertEquals("desc1", result.data?.get(0)?.description ?: "")
    }

    @Test
    fun testGetProducts_expectedError() = runTest {

        Mockito.`when`(productsAPI.getProducts()).thenReturn(Response.error(401, "Unauthorized".toResponseBody()))

        val sut = ProductRepository(productsAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Error)
        Assert.assertEquals("Something went wrong", result.message)
    }

}