package com.example.unittesting.utils

class Helper {

    fun isValidatePassword(password : String) = when {

        password.isBlank() -> {
            "Password can't be blank"
        }
        password.length < 6 -> {
            "Password must be at least 6 characters"
        }
        password.length > 15 -> {
            "Password must be less than 15 characters"
        }
        else -> {
            "Valid"
        }
    }

    fun isPalindrome(input : String) : Boolean {
        var i = 0
        var j = input.length - 1
        var result = true

        while (i < j) {
            if(input[i] != input[j])  {
                result = false
                break
            }
            j--
            i++
        }
        return result
    }


}
