package com.str.unittesting.mocking

class UserService(private val userRepository: UserRepository) {

    fun loginUser(email: String, password : String) : String{
        val status = userRepository.loginUser(email, password)
        return when(status) {
            LOGIN_STATUS.INVALID_USER -> "User doesn't exist"
            LOGIN_STATUS.INVALID_PASSWORD -> "Password is invalid"
            LOGIN_STATUS.UNKOWN_ERROR -> "Unknown error occured"
            LOGIN_STATUS.SUCCESS -> "Logged in successfully"
        }
    }
}
