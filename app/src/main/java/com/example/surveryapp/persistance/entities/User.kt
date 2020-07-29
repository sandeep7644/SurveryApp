package com.example.surveryapp.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String, val username: String, val email: String, val phone: String,
    val password: String
) {
}