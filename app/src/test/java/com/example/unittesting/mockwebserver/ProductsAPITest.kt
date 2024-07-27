package  com.example.unittesting.mockwebserver


import com.example.unittesting.Helper
import com.example.unittesting.api.ProductsAPI
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class ProductsAPITest {

    lateinit var mockWebserver : MockWebServer
    lateinit var productsAPI: ProductsAPI

    @Before
    fun setUp() {
        mockWebserver = MockWebServer()
        productsAPI = Retrofit.Builder()
            .baseUrl(mockWebserver.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductsAPI::class.java)
    }

    @Test
    fun testGetProducts() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]") // This will return empty body response
        mockWebserver.enqueue(mockResponse)

        val response = productsAPI.getProducts()
        mockWebserver.takeRequest() // This will start taking request from mock web server

        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @Test
    fun testGetProducts_returnProducts() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content) // This will return empty body response
        mockWebserver.enqueue(mockResponse)

        val response = productsAPI.getProducts()
        mockWebserver.takeRequest() // This will start taking request from mock web server

        Assert.assertEquals(false, response.body()!!.isEmpty())
        Assert.assertEquals(2, response.body()?.size)
    }

    @Test
    fun testGetProducts_returnError() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong") // This will return empty body response
        mockWebserver.enqueue(mockResponse)

        val response = productsAPI.getProducts()
        mockWebserver.takeRequest() // This will start taking request from mock web server

        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())

    }

    @After
    fun tearDown() {
        mockWebserver.shutdown()
    }


}