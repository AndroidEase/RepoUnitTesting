package com.example.unittesting.utils

import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(value = Parameterized::class)
class HelperParameterized(val inputString: String, val expectedValue : Boolean) {

    lateinit var helper : Helper

    @Before
    fun setUp() {
        helper = Helper()
        println("HelperTest Before")
    }

    @After
    fun tearDown(){
        println("HelperTest Tear Down")
    }

    @Test
    fun isPalindrome() {
        //Arrange
        //Act
        val result = helper.isPalindrome(inputString)
        //Assert
        assertEquals(expectedValue,result)
    }

    companion object {

        @JvmStatic // Because Junit is from Java
        @Parameters(name = "{0} is palindrome - {1}")
        fun data() : List<Array<Any>>{
            return listOf(
                arrayOf("hello", false),
                arrayOf("level", true),
                arrayOf("a", true),
                arrayOf("",true)
            )
        }
    }
}