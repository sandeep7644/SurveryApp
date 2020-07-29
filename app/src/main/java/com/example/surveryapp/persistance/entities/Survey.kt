package com.example.surveryapp.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Survey(
    @PrimaryKey(autoGenerate = true)
    val id : Int=0,
    val survey_name: String,
    val survey_address: String,
    val age: Int,
    val gender: String,
    val last_education: String,
    val want_to_be: String
) {
}