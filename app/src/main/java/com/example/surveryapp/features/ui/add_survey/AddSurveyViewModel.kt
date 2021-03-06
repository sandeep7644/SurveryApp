package com.example.surveryapp.features.ui.add_survey

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surveryapp.data.model.SurveyRepository
import com.example.surveryapp.persistance.entities.Survey
import kotlinx.coroutines.launch

class AddSurveyViewModel @ViewModelInject constructor(
    val surveyRepository: SurveyRepository
) : ViewModel() {

    val surveySaved = MutableLiveData<Long>()

    fun saveSurvey(
        name: String,
        address: String,
        age: Int,
        gender: String,
        last_education: String,
        want_to_be: String
    ) {
        viewModelScope.launch {
           val id = surveyRepository.saveSurvey(
                Survey(
                    survey_name = name, survey_address = address, age = age,
                    gender = gender, last_education = last_education, want_to_be = want_to_be
                )
            )
            surveySaved.postValue(id)
        }
    }

}