package com.example.surveryapp.data.model

import com.example.surveryapp.persistance.dao.SurveyDao
import com.example.surveryapp.persistance.entities.Survey
import javax.inject.Inject

class SurveyRepository @Inject constructor(val surveyDao: SurveyDao) {

     fun getSurveys() = surveyDao.getSurveys()


    suspend fun saveSurvey(
      survey: Survey) = surveyDao.saveSurvey(survey)


}