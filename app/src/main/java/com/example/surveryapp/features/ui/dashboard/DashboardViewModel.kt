package com.example.surveryapp.features.ui.dashboard

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.surveryapp.data.model.SurveyRepository
import com.example.surveryapp.persistance.entities.Survey

class DashboardViewModel @ViewModelInject constructor(val surveyRepository: SurveyRepository,
                                                      val sharedPreferences: SharedPreferences
) :
    ViewModel() {
    lateinit var  surveyList : LiveData<List<Survey>>
    val logout = MutableLiveData<Boolean>()

    fun getSurveys() = surveyRepository.getSurveys()

    fun logout(){
        sharedPreferences.edit().apply{
            clear()
            commit()
            logout.postValue(true)
        }
    }
}