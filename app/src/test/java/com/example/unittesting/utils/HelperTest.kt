package com.example.unittesting.utils

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class HelperTest {

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
        val result = helper.isPalindrome("Rupal")
        //Assert
        assertEquals(false,result)
    }

    @Test
    fun isPalindromeSingleChar() {
        //Arrange
        //Act
        val result = helper.isPalindrome("r")
        //Assert
        assertEquals(true,result)
    }

    @Test
    fun isValidatePassword_blankInput_expected_blankError() {

        val result = helper.isValidatePassword("  ")
        assertEquals("Password can't be blank",result)
    }

    @Test
    fun isValidatePassword_2CharInput_expected_6charExpectedError() {

        val result = helper.isValidatePassword("AB")
        assertEquals("Password must be at least 6 characters",result)
    }

    @Test
    fun isValidatePassword_7CharInput_expected_lessThen6CharExpectedError() {

        val result = helper.isValidatePassword("1234567891234567")
        assertEquals("Password must be less than 15 characters",result)
    }


    @Test
    fun isValidatePassword_2CharInput_expected_validPassword() {

        val result = helper.isValidatePassword("Abc123")
        assertEquals("Valid",result)
    }
}