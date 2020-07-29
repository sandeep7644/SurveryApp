package com.example.surveryapp.persistance.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.surveryapp.persistance.entities.Survey
import com.example.surveryapp.persistance.entities.User

@Dao
interface SurveyDao {

    @Query("SELECT * FROM Survey")
    fun getSurveys(): LiveData<List<Survey>>

    @Insert
    suspend fun saveSurvey(survey: Survey): Long
}