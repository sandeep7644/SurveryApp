package com.example.surveryapp.data.model

import android.content.SharedPreferences
import com.example.surveryapp.persistance.dao.UserDao
import com.example.surveryapp.persistance.entities.User
import javax.inject.Inject

class UserRepository @Inject constructor(val userDao: UserDao,val sharedPreferences: SharedPreferences) {

    suspend fun loginUser(username: String, password: String): User {
        return userDao.loginUser(username, password)
    }

    suspend fun saveUserDetails(
    user: User
) = userDao.saveUserDetails(user)

    suspend fun checkUserExists(username: String) = userDao.checkUserName(username)
suspend fun getCurrentLoggedInUser(): User = userDao.getCurrentUser(sharedPreferences.getInt("user_id",0))
}