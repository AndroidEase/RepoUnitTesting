package com.example.unittesting.mocking

import org.junit.Assert
import org.junit.Before
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Test

class UserServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(userRepository.loginUser(anyString(),anyString())).thenReturn(LOGIN_STATUS.INVALID_PASSWORD)
    }

    @Test
    fun testUserService() {
        val sut = UserService(userRepository)
        val status = sut.loginUser("email1", "pass1")
        Assert.assertEquals("Password is invalid", status)
    }

}