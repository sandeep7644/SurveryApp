package com.example.surveryapp.features.ui.add_survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.surveryapp.R
import com.example.surveryapp.features.ui.dashboard.DashboardActivity
import com.example.surveryapp.features.ui.dashboard.DashboardViewModel
import com.example.surveryapp.features.ui.login.LoginActivity
import com.example.surveryapp.persistance.entities.Survey
import com.example.surveryapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_survey.*
import kotlinx.android.synthetic.main.activity_add_survey.address
import kotlinx.android.synthetic.main.activity_add_survey.age
import kotlinx.android.synthetic.main.activity_add_survey.last_education
import kotlinx.android.synthetic.main.activity_add_survey.name
import kotlinx.android.synthetic.main.activity_resgistration.*
import kotlinx.android.synthetic.main.survey_recyclerview_item.*
import kotlinx.android.synthetic.main.toolbar_layout.*

@AndroidEntryPoint
class AddSurveyActivity : AppCompatActivity() {
    val addSurveyViewModel: AddSurveyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_survey)
        setUpToolbar()
        addSurveyViewModel.surveySaved.observe(this, object : Observer<Long> {
            override fun onChanged(t: Long) {
                if (t > 0) {
                    showToast(getString(R.string.survey_saved_successfully))
                    startActivity(Intent(this@AddSurveyActivity, DashboardActivity::class.java))
                    finish()
                } else {
                    showToast(getString(R.string.survey_failed))

                }
            }


        })

        save_survey.setOnClickListener {
            if (validaFields()) {
                val gender = when (radiogrp_gender.checkedRadioButtonId) {
                    R.id.male -> "Male"
                    R.id.female -> "Female"
                    else -> "Other"
                }
                addSurveyViewModel.saveSurvey(
                    name.text.toString(),
                    address.text.toString()
                    ,
                    age.text.toString().toInt(),
                    gender,
                    last_education.text.toString(),
                    want_to_be.text.toString()
                )
            }
        }
    }

    private fun validaFields(): Boolean {
        if (name.text.toString().isEmpty()) {
            showToast("Please enter name")
            return false
        }

        if (address.text.toString().isEmpty()) {
            showToast("Please enter address")
            return false
        }
        if (age.text.toString().isEmpty()) {
            showToast("Please enter Age")
            return false
        }


        if (want_to_be.text.toString().isEmpty()) {
            showToast("Please tell what you want to be")
            return false
        }

        return true
    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle("Add Survey")
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}