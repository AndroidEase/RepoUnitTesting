package com.str.unittesting.mocking

class UserRepository {

    val users = listOf<User>(
        User(1,"Name 1" , "Email 1","Password 1"),
        User(2,"Name 2" , "Email 2","Password 2"),
    )

    fun loginUser(email : String, password : String) : LOGIN_STATUS {

        val users = users.filter { user ->
            user.email == email
        }

        return if (users.size == 1) {
            if(users[0].password == password) {
                LOGIN_STATUS.SUCCESS
            } else {
                LOGIN_STATUS.INVALID_PASSWORD
            }
        } else {
            LOGIN_STATUS.INVALID_USER
        }
    }
}
