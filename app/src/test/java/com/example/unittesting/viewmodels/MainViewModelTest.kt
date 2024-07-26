package com.example.unittesting.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.unittesting.api.ProductRepository
import com.example.unittesting.utils.NetworkResult
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getProducts() = runTest {
        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Error("Something went wrong"))

        val sut = MainViewModel(repository)
        sut.getProducts()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.products.getOrAwaitValue()
        Assert.assertEquals(0, result.data?.size)
    }

    @Test
    fun getProducts_expectedError() = runTest {
        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Success(emptyList()))

        val sut = MainViewModel(repository)
        sut.getProducts()
        val result = sut.products.getOrAwaitValue()
        Assert.assertEquals(0, result.data?.size)
        Assert.assertEquals(true, result is NetworkResult.Error<*>)
        Assert.assertEquals("Something went wrong", result.message)

    }

}