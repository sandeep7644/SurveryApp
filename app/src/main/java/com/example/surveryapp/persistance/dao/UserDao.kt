package com.example.surveryapp.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.surveryapp.persistance.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    suspend fun loginUser(username: String, password: String): User

    @Query("SELECT * FROM User WHERE id = :id ")
    suspend fun getCurrentUser(id: Int): User

    @Query("SELECT * FROM User WHERE username = :username ")
    suspend fun checkUserName(username: String): User

    @Insert
    suspend fun saveUserDetails(user: User): Long
}