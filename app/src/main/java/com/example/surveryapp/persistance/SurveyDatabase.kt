package com.example.surveryapp.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.surveryapp.persistance.dao.SurveyDao
import com.example.surveryapp.persistance.dao.UserDao
import com.example.surveryapp.persistance.entities.Survey
import com.example.surveryapp.persistance.entities.User

@Database(entities = [User::class, Survey::class], version = 1, exportSchema = true)
abstract class SurveyDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun surveyDao(): SurveyDao
}